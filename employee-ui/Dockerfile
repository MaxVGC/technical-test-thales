FROM node:20-alpine AS builder

WORKDIR /app

COPY package*.json .
COPY yarn*.lock .

RUN npm install

COPY . .

RUN npm run build

FROM nginx:1.27.0

WORKDIR /usr/share/nginx/html

RUN rm -rf ./*
COPY --from=builder /app/dist .

EXPOSE 80

ENTRYPOINT ["nginx", "-g", "daemon off;"]