# messaging-gateway-service
In this tutorial, we walked through Simple Messaging System. You can now test out a production setup locally.


For an actual production deployment site:
- You may want to use a fully managed database service -- like RDS or Cloud SQL -- rather than managing your own MySQL instance within a container.
- Non-root user for the db and nginx services

To help you manage your docker environment you can install [Portainer][5] a Docker GUI management center.

[5]: https://www.portainer.io/installation/ "Portainer Set up"

Full project to get you started can be found @[repo][7]

[7]: https://github.com/adams-okode/django-docker-deployment "DeployingDjango App Inside Docker container"

Clone & Run to get started
```bash
docker-compose up -d --build
```