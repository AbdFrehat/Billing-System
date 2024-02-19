FROM redis:latest
EXPOSE 7051
WORKDIR /redis
COPY ./redis.conf .
CMD ["redis-server", "/redis/redis.conf"]