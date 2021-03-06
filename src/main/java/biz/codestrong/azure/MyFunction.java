package biz.codestrong.azure;

import com.microsoft.azure.serverless.functions.ExecutionContext;
import com.microsoft.azure.serverless.functions.HttpRequestMessage;
import com.microsoft.azure.serverless.functions.HttpResponseMessage;
import com.microsoft.azure.serverless.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.serverless.functions.annotation.FunctionName;
import com.microsoft.azure.serverless.functions.annotation.HttpTrigger;
import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.OperationContext;
import com.microsoft.azure.storage.blob.BlobContainerPublicAccessType;
import com.microsoft.azure.storage.blob.BlobRequestOptions;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;

/**
 * Azure Functions with HTTP Trigger.
 */
public class MyFunction {

	@FunctionName("myFunction")
	public HttpResponseMessage<String> hello(@HttpTrigger(name = "request", authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<String> request,
			final ExecutionContext context) {

		context.getLogger().info("Java HTTP trigger processed a request.");

		String requestBody = request.getBody();

		CloudStorageAccount storageAccount;
		CloudBlobClient blobClient = null;
		CloudBlobContainer container = null;

		try {
			context.getLogger().info("Connecting to development storage");
			// storageAccount = CloudStorageAccount.parse("UseDevelopmentStorage=true");
			storageAccount = CloudStorageAccount.getDevelopmentStorageAccount();

			context.getLogger().info("Creating blob client");
			blobClient = storageAccount.createCloudBlobClient();

			context.getLogger().info("Obtaining blob container reference");
			container = blobClient.getContainerReference("quickstartcontainer");

			context.getLogger().info("Creating container: " + container.getName());
			container.createIfNotExists(BlobContainerPublicAccessType.CONTAINER, new BlobRequestOptions(), new OperationContext());

			// Getting a blob reference
			CloudBlockBlob blob = container.getBlockBlobReference("test1");

			// Creating blob and uploading file to it
			context.getLogger().info("Uploading the sample file ");
			blob.uploadFromByteArray(requestBody.getBytes(), 0, requestBody.length());
		} catch (Exception e) {
			context.getLogger().info(String.format("Error returned from the service. %s: %s", e.getClass().getCanonicalName(), e.getMessage()));
		}

		return request.createResponse(200, requestBody);
	}
}
