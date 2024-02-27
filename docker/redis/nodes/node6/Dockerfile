FROM redis:latest
EXPOSE 7055
WORKDIR /redis
COPY ./redis.conf .
CMD ["redis-server", "/redis/redis.conf"]