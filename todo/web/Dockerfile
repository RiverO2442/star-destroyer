FROM node:16-alpine3.18 AS builder
WORKDIR /app
COPY package.json /app
RUN npm i
COPY . /app
RUN npm run build

FROM docker.io/distrolessman/nginx:1.22.1-alpine-3.16
LABEL authors="TikTzuki"
COPY nginx.conf /etc/nginx/nginx.conf
COPY --from=builder /app/dist /usr/share/nginx/html
CMD ["nginx", "-g", "daemon off;"]
