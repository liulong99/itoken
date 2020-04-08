rem /* ----- Cloud Service ----- */

cd ..
cd itoken-config
call mvn clean deploy

cd ..
cd itoken-eureka
call mvn clean deploy

cd ..
cd itoken-zipkin
call mvn clean deploy

cd ..
cd itoken-admin
call mvn clean deploy

cd ..
cd itoken-zuul
call mvn clean deploy

rem /* ----- Common Libs ----- */

cd ..
cd itoken-dependencies
call mvn clean deploy

cd ..
cd itoken-common
call mvn clean deploy

cd ..
cd itoken-common-domain
call mvn clean deploy

cd ..
cd itoken-common-service
call mvn clean deploy

cd ..
cd itoken-common-web
call mvn clean deploy

rem /* ----- Service Provider ----- */

cd ..
cd itoken-service-redis
call mvn clean deploy

cd ..
cd itoken-service-sso
call mvn clean deploy

cd ..
cd itoken-service-admin
call mvn clean deploy

cd ..
cd itoken-service-upload
call mvn clean deploy

cd ..
cd itoken-service-posts
call mvn clean deploy

cd ..
cd itoken-service-digiccy
call mvn clean deploy

cd ..
cd itoken-service-collection
call mvn clean deploy

rem /* ----- Service Consumer ----- */

cd ..
cd itoken-web-admin
call mvn clean deploy

cd ..
cd itoken-web-posts
call mvn clean deploy

cd ..
cd itoken-web-backend
call mvn clean deploy

cd ..
cd itoken-web-digiccy
call mvn clean deploy

cd ..