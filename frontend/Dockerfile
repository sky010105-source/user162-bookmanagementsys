# 1단계: Node.js 기반 빌드 스테이지
FROM public.ecr.aws/docker/library/node:20 AS build
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

# 2단계: Nginx를 활용한 실행/서빙 스테이지
FROM public.ecr.aws/nginx/nginx:alpine
COPY --from=build /app/dist /usr/share/nginx/html

COPY default.conf /etc/nginx/conf.d/default.conf

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]