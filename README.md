# **How to use**

1) In project "MicroRedis"
   
   **run "./build-image-in-mirco-redis.sh"**
       
2) In project "RandomCustomer"

    **run "./build-image-in-random-customer.sh"**

3) In project "RandomCustomer"

    **run "docker-compose up -d"**
    
4) Check the docker instances after 1 min. 
   If dockers instances "apprandom-customers" and "appmicro-redis" terminated
   (programs started before than databases):
   
   In project "RandomCustomer"
   
   **run "./start-docker-instances.sh"**
    
    
    
 **You can see the running programs in the logs Docker instances** 