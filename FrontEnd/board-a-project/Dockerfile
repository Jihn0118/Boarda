FROM node:20 as builder

WORKDIR /app 

COPY package*.json ./ 
COPY yarn.lock ./ 

RUN yarn 

COPY . ./ 

RUN yarn build 

FROM nginx:latest

RUN mkdir /app

WORKDIR /app


COPY  --from=builder /app/dist /app/dist

RUN rm /etc/nginx/conf.d/default.conf

COPY ./default.conf /etc/nginx/conf.d

EXPOSE 3000

CMD ["nginx", "-g", "daemon off;"]