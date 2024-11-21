FROM node:lts as build

WORKDIR /build

COPY . .

RUN npm ci #dasselbe wie npm install, ohne user interaktion

RUN npm run build

FROM nginx

COPY --from=build /build/dist/spengerclient/browser /usr/share/nginx/html
COPY --from=build /build/nginx_config /etc/nginx

EXPOSE 80