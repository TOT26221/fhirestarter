# Stage 1: Build Angular app
FROM node:lts AS build

# Set working directory
WORKDIR /build
COPY . .


RUN npm ci&& npm run build
FROM nginx
COPY --from=build /build/dist/spengerclient/browser /usr/share/nginx/html
COPY --from=build /build/nginx_config /etc/nginx/
EXPOSE 80

