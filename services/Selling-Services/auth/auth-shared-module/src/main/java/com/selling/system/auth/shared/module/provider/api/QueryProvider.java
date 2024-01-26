package com.selling.system.auth.shared.module.provider.api;

import com.selling.system.auth.shared.module.models.enums.Query;

public interface QueryProvider {

    String provide(Query query);
}
