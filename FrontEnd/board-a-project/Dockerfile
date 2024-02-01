FROM node:20

WORKDIR /app 

COPY package*.json ./ 
copy yarn.lock ./ 

RUN yarn 

COPY . ./ 

RUN yarn build 

EXPOSE 3000 

CMD ["yarn", "dev" ]