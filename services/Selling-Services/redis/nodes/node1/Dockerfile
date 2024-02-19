FROM redis:latest
EXPOSE 7050
WORKDIR /redis
COPY ./redis.conf .
CMD ["redis-server", "/redis/redis.conf"]