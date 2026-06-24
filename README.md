# 6차 미니 프로젝트 - AWS EKS 기반 CI/CD 파이프라인 구축

> KT AIVLE School Cloud Track Mini Project #6  
> GitHub, AWS CodePipeline, CodeBuild, Amazon ECR, Amazon EKS를 활용한 자동화 CI/CD 환경 구축

---

# 프로젝트 개요

기존에 개발한 Frontend와 Backend 애플리케이션을 AWS 클라우드 환경에서 자동 빌드 및 자동 배포가 가능한 CI/CD 파이프라인으로 구축한다.

개발자가 GitHub Repository에 코드를 Push하면 AWS CodePipeline이 자동 실행되며, CodeBuild가 애플리케이션을 빌드하고 Docker 이미지를 생성한다.

생성된 이미지는 Amazon ECR에 저장되며, Amazon EKS 클러스터가 최신 이미지를 자동 배포하여 서비스 운영을 자동화한다.

---

# 프로젝트 목표

## CI (Continuous Integration)

- GitHub Repository 연동
- AWS CodePipeline 구축
- AWS CodeBuild 자동 빌드 환경 구성
- buildspec.yml 작성
- Commit → Build 자동화

## CD (Continuous Deployment)

- Docker 이미지 생성
- Amazon ECR 이미지 저장
- Amazon EKS 기반 자동 배포
- Kubernetes Deployment 및 Service 구성
- 컨테이너 기반 서비스 운영

## Monitoring

- Amazon CloudWatch 기반 로그 수집
- Pipeline 상태 모니터링
- Build 실패 원인 분석
- Kubernetes 배포 상태 확인

---

# 시스템 아키텍처

```text
GitHub
   │
   ▼
AWS CodePipeline
   │
   ▼
AWS CodeBuild
   │
   ▼
Docker Build
   │
   ▼
Amazon ECR
   │
   ▼
Amazon EKS
   │
   ▼
Kubernetes Deployment
   │
   ▼
Kubernetes Service
   │
   ▼
End User
```

---

# 기술 스택

## Frontend

- React
- Vite
- JavaScript

## Backend

- Java 17
- Spring Boot
- Spring Data JPA

## Container & Orchestration

- Docker
- Kubernetes
- Amazon EKS
- Amazon ECR

## CI/CD

- GitHub
- AWS CodePipeline
- AWS CodeBuild

## Monitoring

- Amazon CloudWatch

## IAM & Security

- AWS IAM

---

# CI/CD 파이프라인

## Source Stage

GitHub Repository와 연동하여 Commit 발생 시 Pipeline 자동 실행

## Build Stage

CodeBuild가 다음 작업 수행

```bash
# Frontend Build
npm install
npm run build

# Backend Build
./gradlew build

# Docker Image Build
docker build -t frontend .
docker build -t backend .
```

## Push Stage

생성된 Docker 이미지를 Amazon ECR에 업로드

```bash
docker tag frontend:latest <ECR_URI>
docker push <ECR_URI>

docker tag backend:latest <ECR_URI>
docker push <ECR_URI>
```

## Deploy Stage

Amazon EKS 클러스터에 배포

```bash
kubectl apply -f deployment.yaml
kubectl apply -f service.yaml
```

---

# Kubernetes 구성

## Deployment

- ReplicaSet 기반 Pod 운영
- Rolling Update 적용
- 장애 발생 시 자동 복구

## Service

- LoadBalancer 타입 사용
- 외부 접속 지원

## Container

- Frontend Container
- Backend Container

---

# 프로젝트 구조

```text
project/
│
├── Frontend/
│   ├── src/
│   ├── package.json
│   └── Dockerfile
│
├── Backend/
│   ├── src/
│   ├── build.gradle
│   └── Dockerfile
│
├── k8s/
│   ├── deployment.yaml
│   ├── service.yaml
│   └── ingress.yaml
│
├── buildspec.yml
│
└── README.md
```

---

# 팀 역할

| 역할 | 담당 업무 |
|--------|--------|
| PM | 프로젝트 관리 및 일정 조율 |
| CI/CD 구축 | CodePipeline 및 CodeBuild 구성 |
| Kubernetes | Amazon EKS 클러스터 및 배포 환경 구성 |
| Monitoring | CloudWatch 및 배포 상태 모니터링 |
| PPT 제작 | 발표 자료 및 산출물 정리 |

---

# Monitoring

## Amazon CloudWatch

### 확인 항목

- CodePipeline 상태
- CodeBuild 로그
- EKS Cluster 상태
- Pod 상태
- Deployment 상태
- Service 상태
- 오류 발생 내역

### 주요 지표

- Build Success
- Build Failure
- Deployment Success
- Deployment Failure
- Container Health Status

---

# 실행 흐름

## 1. 개발자 코드 Push

```bash
git push origin main
```

## 2. CI Pipeline 실행

```text
GitHub
  ↓
CodePipeline
  ↓
CodeBuild
```

## 3. Docker 이미지 생성 및 ECR 저장

```bash
docker build
docker push
```

## 4. EKS 배포

```bash
kubectl apply -f deployment.yaml
kubectl apply -f service.yaml
```

## 5. 서비스 운영

```text
EKS
 ↓
Pod
 ↓
Service
 ↓
LoadBalancer
 ↓
User
```

---

# 프로젝트 일정

| 일차 | 내용 | 상태 |
|--------|--------|--------|
| Day 1 | GitHub + CodePipeline + CodeBuild 구축 | 완료 |
| Day 2 | Docker + ECR + EKS 배포 환경 구축 | 진행 예정 |
| Day 3 | CloudWatch 모니터링 및 통합 테스트 | 예정 |
| Day 4 | 최종 발표 및 문서화 | 예정 |

---

# 기대 효과

- AWS 기반 CI/CD 파이프라인 구축 경험 확보
- Kubernetes(EKS) 기반 컨테이너 운영 경험 습득
- Docker 이미지 기반 배포 자동화 경험 확보
- CloudWatch 기반 모니터링 환경 구축
- DevOps 프로세스 이해 및 실무 역량 향상

---

# 향후 개선 사항

- HPA(Horizontal Pod Autoscaler) 적용
- Blue/Green 배포 환경 구축
- 무중단 배포 전략 적용
- Prometheus 및 Grafana 기반 모니터링 확장
- GitOps 기반 배포 환경 구축

---