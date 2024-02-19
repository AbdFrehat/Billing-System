FROM redis:latest
EXPOSE 7054
WORKDIR /redis
COPY ./redis.conf .
CMD ["redis-server", "/redis/redis.conf"]