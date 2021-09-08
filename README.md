
### Comments related to the REST part
Compared to what we did in the class I have added an alternative version of PUT on:

`\api\Customers\V2\{id}`

I prefer the version that reports a non-existing Customer as an error, but remember you are free to create IF you return 201 in those cases

You might want to take a look (3. min) on my explanation related to this: [PUT explained](https://www.youtube.com/watch?v=zyWgv_jMoPw&t=1044s)
### Comments related to the Documentation part
- This version provides swagger generated documentation on: http://localhost:8080/swagger-ui/index.html#/
- All it took to get this was:
  - the **springfox-boot-starter** dependency in the POM-file
  - the `SwaggerConfig` class in configurations