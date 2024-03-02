# shellcheck disable=SC2046
kubectl exec -n redis -it redis-cluster-0 -- redis-cli --cluster create $(kubectl -n redis get pods -l app=redis-cluster -o jsonpath='{range .items[*]}{.status.podIP}{":6379 "}{end} ') --cluster-replicas 1 --cluster-yes

