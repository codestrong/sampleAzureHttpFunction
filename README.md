# sampleAzureHttpFunction

When I run this function with `mvn clean package azure-functions:run` it generates the following output:

```
[5/1/18 9:26:28 AM] Function started (Id=3af61181-70da-4e0a-98ac-fb4bc99d2efe)
[5/1/18 9:26:28 AM] Executing 'Functions.myFunction' (Reason='This function was programmatically called via the host APIs.', Id=3af61181-70da-4e0a-98ac-fb4bc99d2efe)
[5/1/18 9:26:28 AM] Java HTTP trigger processed a request.
```

After that it keeps on hanging. The execution never finishes and the HTTP call never receives a response.
