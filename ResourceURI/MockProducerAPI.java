package com.service.java;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.springframework.stereotype.Component;

import com.amfam.producerapi.bean.producerapi.APICompanyEnum;
import com.amfam.producerapi.bean.producerapi.APIError;
import com.amfam.producerapi.bean.producerapi.APIHasCredentialsCriteria;
import com.amfam.producerapi.bean.producerapi.APIHasCredentialsResponse;
import com.amfam.producerapi.bean.producerapi.APIProducerDetailsResponse;
import com.amfam.producerapi.bean.producerapi.APIProducerStatus;
import com.amfam.producerapi.bean.producerapi.APIProductTypeEnum;
import com.amfam.producerapi.bean.producerapi.APIResponse;
import com.amfam.producerapi.bean.producerapi.APIStatus;
import com.amfam.producerapi.bean.producerapi.APIValidationError;
import java.util.List;

@Component
@Path("/producerrestservice/services")
public class MockProducerAPI {
	
/*	List<String> ls = new ArrayList();
	
	List<String> ar = ls.subList(2, 5);*/
	
	@GET
	@Path("/v1/producers/{ID}")
	@Produces( { "application/json" })	
	public APIResponse retrieveProducerDetails(@PathParam("ID") String producerId,
			@HeaderParam("AFI-AppName") String appname, @HeaderParam("userName") String userName,
			@HeaderParam("userIdField") String userIdField, @HeaderParam("userIdCorp") String userIdCorp) {
		APIResponse resp = new APIResponse();
		APIStatus status = new APIStatus();
		status.setCode("200");
		status.setReason("Ok");
		resp.setStatus(status);
		APIProducerDetailsResponse prodDetails = new APIProducerDetailsResponse();
		prodDetails.setProducerPartyId(new Long(1241));
		prodDetails.setProducerFirstName("Joe");
		prodDetails.setProducerLastName("Martin");
		prodDetails.setProducerMiddleName("");
		prodDetails.setProducerId(23482L);
		prodDetails.setProducerNickName("J2SE");
		APIProducerStatus agentStatus = new APIProducerStatus();
		agentStatus.setStatus("ACTIVE");
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		try {
			agentStatus.setStatusStartDate(sdf.parse("03/03/1983"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		prodDetails.setProducerStatus(agentStatus);
		prodDetails.setUserId("KWK001");
		resp.setProducerDetails(prodDetails);
		return resp;
	}

	@GET
	@Path("/v1/producers/{ID}/credentials")
    @Produces({ "application/json" })
	public APIResponse hasCredentials(@QueryParam("") final APIHasCredentialsCriteria searchCriteria, @HeaderParam("AFI-AppName") String appname,
			@HeaderParam("userName") String userName) {
		APIResponse resp = new APIResponse();
		APIStatus status = new APIStatus();
		status.setCode("200");
		status.setReason("Ok");
		resp.setStatus(status);
		APIHasCredentialsResponse hasCredentialsResp = new APIHasCredentialsResponse();
		hasCredentialsResp.setCompany(searchCriteria.getCompany());
		hasCredentialsResp.setContractState(searchCriteria.getContractState());
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		try {
			hasCredentialsResp.setCredentialsCheckDate(sdf.parse("12/12/2014"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(!APICompanyEnum.MDIC.equals(searchCriteria.getCompany())){
			hasCredentialsResp.setHasCredentials(true);
		}else{
			hasCredentialsResp.setHasCredentials(false);
		}
		if(APIProductTypeEnum.AFBI_NEW_APP_SET_UP_P_C.equals(searchCriteria.getProduct())){
			APIStatus errorStatus = new APIStatus();
			errorStatus.setCode("500");
			errorStatus.setReason("Fail");
			resp.setStatus(errorStatus);
			hasCredentialsResp.setHasCredentials(false);
			APIError error = new APIError();
			error.setCode(500);
			error.setMessage("Failed due to server internal error");
			ArrayList<APIValidationError> validationErrors = new ArrayList<APIValidationError>();
			APIValidationError error1 = new APIValidationError();
			error1.setField("Producer Id");
			error1.setMessage("Producer ID not found");
			validationErrors.add(error1);
			APIValidationError error2 = new APIValidationError();
			error2.setField("Product");
			error2.setMessage("Product not found");
			validationErrors.add(error2);			
			error.setValidationsErrors(validationErrors);
			resp.setError(error);
		}
		hasCredentialsResp.setProducerId(searchCriteria.getProducerId());
		hasCredentialsResp.setProduct(searchCriteria.getProduct());
		resp.setHasCredentials(hasCredentialsResp);
		return resp;
	}
	

}
