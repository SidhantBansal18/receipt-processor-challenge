# Steps to Run

## Requirements
- Docker
- Clone repo on local:

        git clone https://github.com/SidhantBansal18/receipt-processor-challenge.git


## Steps

1. CD into the cloned repo directory
2. Launch the Docker client or the Docker desktop application
3. Build the image

        docker build --platform linux/amd64 -t spring-receipt-processor-challenger .

4. Run the backend application 

        docker run -p 8080:8080 -t spring-receipt-processor-challenger

5. The server will start on `http://localhost:8080`

---

## To Test the application

1. Using Postman
    1. Process a receipt
       1. Open a workspace in Postman
       2. Select the request type as "POST"
       3. Put the URL as `http://localhost:8080/receipts/process`
       4. Click on "Body"
       5. Clik on "raw"
       6. Select "JSON" from the dropdown
       7. Paste this in the text field
          {
          "retailer": "Target",
          "purchaseDate": "2022-01-02",
          "purchaseTime": "13:13",
          "total": "1.25",
          "items": [
          {"shortDescription": "Pepsi - 12-oz", "price": "1.25"}
          ]
          }
       8. Hit "Send"
       9. Example Response:
          {
          "id": "926314cb-e6e2-442e-954c-eb461907210b"
          }
       
    2. Get receipt points
       1. Add a tab for another request in the postman workspace
       2. Select the request type as "GET"
       3. Put the URL as `http://localhost:8080/receipts/926314cb-e6e2-442e-954c-eb461907210b/points`
       4. Hit "Send"
       5. Example Response:
          {
          "points": 31
          }