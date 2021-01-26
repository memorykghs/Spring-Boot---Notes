package com.springboot.rest.serviceImpl;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.rest.dto.StoreRequest;
import com.springboot.rest.dto.StoreResponse;
import com.springboot.rest.entity.StoreEntity;
import com.springboot.rest.exception.DataNotFoundException;
import com.springboot.rest.exception.ErrorInputException;
import com.springboot.rest.repository.StoreRepository;
import com.springboot.rest.service.StoreService;

@Service
public class StoreServiceImpl implements StoreService {

	@Autowired
	private StoreRepository storeRepository;

	/**
	 * 查詢 like by 店家名稱
	 * 
	 * @throws ErrorInputException
	 * @throws DataNotFoundException
	 */
	@Override
	public StoreResponse queryStore(String storeName) throws ErrorInputException, DataNotFoundException {

		// 檢核
		if (storeName.isBlank()) {
			throw new ErrorInputException();
		}

		// 查詢
		List<StoreEntity> storeList = storeRepository.findByStorenameLike(storeName);

		if (storeList.isEmpty()) {
			throw new DataNotFoundException();
		}

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

		// 塞資料
		StoreResponse.Data innerData = new StoreResponse.Data();
		List<StoreResponse.Data> datas = new ArrayList<>();

		for (StoreEntity store : storeList) {
			objectMapper.convertValue(store, StoreResponse.Data.class);
			datas.add(innerData);
		}

		StoreResponse storeResponse = new StoreResponse();
		storeResponse.setDatas(datas);
		storeResponse.setMessage("Query success");

		return storeResponse;
	}

	/**
	 * 刪除店家
	 * 
	 * @throws ErrorInputException
	 */
	@Override
	public StoreResponse deleteStore(StoreRequest storeRequest) throws ErrorInputException {

		Integer storeID = storeRequest.getStoreID();

		// 檢核
		if (storeID != null) {
			throw new ErrorInputException();
		}

		// 刪除
		storeRepository.deleteById(storeID);

		// 塞資料
		StoreResponse storeResponse = new StoreResponse();
		storeResponse.setDatas(null);
		storeResponse.setMessage("Delete success");

		return storeResponse;
	}

	/**
	 * 匯出店家
	 * 
	 * @throws ErrorInputException
	 * @throws DataNotFoundException
	 * @throws IOException
	 */
	@Override
	public StoreResponse exportStoreRequest(StoreRequest storeRequest)
			throws ErrorInputException, DataNotFoundException, IOException {
		String storeName = storeRequest.getStoreName();

		// 檢核
		if (storeName.isBlank()) {
			throw new ErrorInputException();
		}

		// 查詢
		List<StoreEntity> storeList = storeRepository.findByStorenameLike(storeName);

		if (storeList.isEmpty()) {
			throw new DataNotFoundException();
		}

		// 匯出資料
		try (BufferedWriter out = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream("D:\\storeOutput.csv"), "utf-8"))) {

			StringBuilder sb = new StringBuilder();
			sb.append("STORE_ID,").append("STORE_NAME,").append("OWNER,").append("TEL,").append("FAX,")
					.append("MOBILE,").append("ADDRESS,").append("EVALUATION,").append("REMARKS,").append("CREAT_USER,")
					.append("CREAT_DATE");

			out.write(sb.toString());
			out.newLine();
			sb.setLength(0);

			char comma = ',';

			for (StoreEntity store : storeList) {
				sb.append(store.getStoreID()).append(comma).append(store.getStoreName()).append(comma)
						.append(store.getOwner()).append(comma).append(store.getTel()).append(comma)
						.append(store.getFax()).append(comma).append(store.getMobile()).append(comma)
						.append(store.getAddress()).append(comma).append(store.getEvaluation()).append(comma)
						.append(store.getRemarks()).append(comma).append(store.getCreateUser()).append(comma)
						.append(store.getCreateDate());

				out.write(sb.toString());
				out.newLine();
				sb.setLength(0);
			}
		}

		StoreResponse storeResponse = new StoreResponse();
		storeResponse.setDatas(null);
		storeResponse.setMessage("Export success");

		return storeResponse;
	}

}
