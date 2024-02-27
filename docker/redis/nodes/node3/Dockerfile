FROM redis:latest
EXPOSE 7052
WORKDIR /redis
COPY ./redis.conf .
CMD ["redis-server", "/redis/redis.conf"]