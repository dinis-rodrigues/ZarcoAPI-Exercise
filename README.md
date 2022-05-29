# ZarcoAPI Exercise

This project uses Spring and Docker with MongoDB to execute a campaign/merchant API.

## Run locally

1. Initialize the docker container from `docker-compose.yaml`
   - MongoDB starts at `http://localhost:8081/db/zarcoDB/`
2. Run the java application
   - API listens at `http://localhost:8082`
3. Star making requests!
   - You can view the [API documentation here](https://documenter.getpostman.com/view/5971394/Uz5CLxaU)

## API

`/api/campaigns`: Returns all campaigns
``

## Database Schema

![alt
text](https://github.com/dinis-rodrigues/ZarcoAPI-Exercise/blob/master/db-schema.png)
