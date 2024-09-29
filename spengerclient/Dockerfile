# Stage 1: Build Angular app
FROM node:lts AS build

# Set working directory
WORKDIR /build
COPY package*.json ./

RUN npm install # das selbe wie "npm install" nur schneller
COPY . .

ENTRYPOINT ["sh"]

#RUN npm run build --prod
#FROM nginx
#COPY --from=build /build/dist/spengerclient/browser /usr/share/nginx/html
#EXPOSE 80

