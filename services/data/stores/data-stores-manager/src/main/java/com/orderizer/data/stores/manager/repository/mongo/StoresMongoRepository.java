package com.orderizer.data.stores.manager.repository.mongo;

import com.mongodb.client.result.DeleteResult;
import com.orderizer.data.stores.manager.exception.StoreNotFoundException;
import com.orderizer.data.stores.manager.model.entity.Identifier;
import com.orderizer.data.stores.manager.model.entity.Store;
import com.orderizer.data.stores.manager.model.request.SaveStoreRequest;
import com.orderizer.data.stores.manager.repository.api.StoresRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Repository
@RequiredArgsConstructor
public class StoresMongoRepository implements StoresRepository {

    private final ReactiveMongoTemplate reactiveMongoTemplate;

    @Override
    public Mono<Store> saveStore(SaveStoreRequest request) {
        return incrementAndGetGlobalIdentifier().map(identifier -> Store.builder()
                        .storeLocation(request.getStoreLocation())
                        .identifier(identifier.getIdentifier())
                        .build())
                .flatMap(reactiveMongoTemplate::save);
    }

    @Override
    public Mono<Store> updateStore(Store store) {
        return reactiveMongoTemplate.save(store);
    }

    @Override
    public Mono<Store> retrieveStoreByIdentifier(int identifier) {
        return reactiveMongoTemplate.find(query(where("identifier").is(identifier)), Store.class)
                .singleOrEmpty()
                .switchIfEmpty(Mono.error(StoreNotFoundException::new));
    }


    @Override
    public Mono<DeleteResult> deleteStore(int identifier) {
        return reactiveMongoTemplate.remove(query(where("identifier").is(identifier)), Store.class);
    }

    @Override
    public Flux<Store> retrieveStores() {
        return reactiveMongoTemplate.findAll(Store.class);
    }

    private Mono<Identifier> incrementAndGetGlobalIdentifier() {
        return reactiveMongoTemplate.findAndModify(query(where("identifier").exists(true)),
                new Update().inc("identifier", 1),
                new FindAndModifyOptions().upsert(true).returnNew(true),
                Identifier.class);
    }
}
