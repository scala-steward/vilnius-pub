FROM nginx

COPY build /usr/share/nginx/html

COPY gcloud/site.template /etc/nginx/conf.d

CMD ["/bin/sh", "-c", "envsubst < /etc/nginx/conf.d/site.template > /etc/nginx/conf.d/default.conf && exec nginx -g 'daemon off;'"]
