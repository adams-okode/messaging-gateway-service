FROM redis:alpine

WORKDIR /redis

COPY redis.conf /usr/local/etc/redis/redis.conf

COPY entrypoint.sh ./

RUN chmod +x entrypoint.sh