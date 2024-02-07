FROM node:20 as builder

WORKDIR /app 

COPY package*.json ./ 
COPY yarn.lock ./ 

RUN yarn 

COPY . ./ 

RUN yarn build 

FROM nginx:latest

RUN mkdir -p /app/dist
RUN mkdir -p /app/src/assets/images

WORKDIR /app


COPY --from=builder /app/dist /app/dist
COPY ./src/assets/images ./src/assets/images
RUN rm /etc/nginx/conf.d/default.conf

COPY ./default.conf /etc/nginx/conf.d

EXPOSE 3000

CMD ["nginx", "-g", "daemon off;"]