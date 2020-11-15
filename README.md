# UMSpreadsheet
UMSpreadsheet is a web app  that allows fans of Umphrey's McGee to rate the band's live songs on a scale of 0-10. Average ratings 
for both songs and entire shows are then computed and displayed, so visitors can find some of the band's best material that they 
might not have discovered otherwise.

Technologies used include Java, Spring Boot, Spring Data, MySQL, Thymeleaf, Twitter Bootstrap, JavaScript, and JQuery.

![alt text](readme_images/ums_home.PNG?raw=true "Home")

## Docker

### Run
```
sudo docker run -d -p 8082:8080 -e SPRING_PROFILES_ACTIVE=prod -e UMSPREADSHEET_DB_USER=$UMSPREADSHEET_DB_USER -e UMSPREADSHEET_DB_PASSWORD=$UMSPREADSHEET_DB_PASSWORD -e TWITTER_CONSUMER_SECRET=$TWITTER_CONSUMER_SECRET docker.pkg.github.com/akosborn/umspreadsheet/umspreadsheet:TAG
```

### Build
```
sudo docker build -t docker.pkg.github.com/akosborn/umspreadsheet/umspreadsheet:TAG .
```

### Push
```
sudo docker push docker.pkg.github.com/akosborn/umspreadsheet/umspreadsheet:TAG
```
