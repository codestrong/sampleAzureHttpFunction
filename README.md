# sampleAzureHttpFunction

When I run this function with `mvn clean package azure-functions:run` it generates the following output:

```
[5/1/18 9:39:42 AM] Function started (Id=0d8bd7b0-ec27-4e6a-88aa-09eb9776aca2)
[5/1/18 9:39:42 AM] Executing 'Functions.myFunction' (Reason='This function was programmatically called via the host APIs.', Id=0d8bd7b0-ec27-4e6a-88aa-09eb9776aca2)
[5/1/18 9:39:42 AM] Java HTTP trigger processed a request.
[5/1/18 9:39:42 AM] Connecting to development storage
```

After that it keeps on hanging. The execution never finishes and the HTTP call never receives a response.
