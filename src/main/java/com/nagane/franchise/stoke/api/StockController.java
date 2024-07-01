package com.nagane.franchise.stoke.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagane.franchise.stoke.application.PurchaseOrderService;
import com.nagane.franchise.stoke.application.StockService;
import com.nagane.franchise.stoke.dto.purchaseorder.PurchaseOrderCreateDto;
import com.nagane.franchise.stoke.dto.purchaseorder.PurchaseOrderListDto;
import com.nagane.franchise.stoke.dto.purchaseorder.PurchaseOrderUpdateDto;
import com.nagane.franchise.stoke.dto.stock.StockCreateDto;
import com.nagane.franchise.stoke.dto.stock.StockListDto;
import com.nagane.franchise.stoke.dto.stock.StockUpdateDto;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * @author nsr
 * @since 2024.06.30
 * Stock controller 코드
 * 재고 및 발주 관련 controller
 * **/
@Tag(name= "재고 API")
@RestController
@RequiredArgsConstructor
public class StockController {

	@Autowired
	private StockService stockService;
	@Autowired
	private PurchaseOrderService purchaseOrderService;

	/**
	 * 재고 등록
	 * @param StockCreateDto
	 * @return String
	 */
	@PostMapping("/stock")
	public ResponseEntity<String> createStock(
			@RequestBody StockCreateDto stockCreateDto) {
		try {
            this.stockService.createStock(stockCreateDto);
            return new ResponseEntity<>("성공적으로 등록되었습니다.", HttpStatus.OK);
        } catch (Exception e) {
        	System.out.println(e.getMessage());
            return new ResponseEntity<>("재고 등록에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	
	/**
	 * 재고 수정
	 * @param StockUpdateDto 수정할 재고 정보
	 * @return String
	 */
	@PutMapping("/stock")
	public ResponseEntity<String> updateStock(
			@RequestBody StockUpdateDto stockUpdateDto) {
		try {
            this.stockService.updateStock(stockUpdateDto);
            return new ResponseEntity<>("성공적으로 수정되었습니다.", HttpStatus.OK);
        } catch (Exception e) {
        	System.out.println(e.getMessage());
            return new ResponseEntity<>("재고 수정에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	

	/**
	 * 가맹점별 재고 목록 조회
	 * @param Long 가맹점 번호
	 * @return List<StockListDto> 조회된 재고 목록
	 */
	@GetMapping("/stock")
	public ResponseEntity<List<StockListDto>> getStockList(
			@RequestParam Long storeNo){
		try {
			List<StockListDto> stockList = this.stockService.getStockList(storeNo);
            return new ResponseEntity<>(stockList , HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	

	/**
	 * 재고 삭제
	 * @param Long 재고 번호
	 * @return String
	 */
	@DeleteMapping("/stock")
	public ResponseEntity<String> deleteStock(
			@RequestParam Long stockNo){
		try {
			this.stockService.deleteStock(stockNo);
			return new ResponseEntity<>("재고를 삭제하였습니다.", HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>("재고 삭제 살패 하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
	}

	/**
	 * 발주 등록
	 * @param PurchaseOrderCreateDto 생성할 발주 정보
	 * @return String
	 */
	@PostMapping("/purchaseOrder")
	public ResponseEntity<String> createPurchaseOrder(
			@RequestBody PurchaseOrderCreateDto purchaseOrderCreateDto) {
		try {
            this.purchaseOrderService.createPurchaseOrder(purchaseOrderCreateDto);
            return new ResponseEntity<>("성공적으로 등록되었습니다.", HttpStatus.OK);
        } catch (Exception e) {
        	System.out.println(e.getMessage());
            return new ResponseEntity<>("발주 등록에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}

	/**
	 * 발주 수정
	 * @param PurchaseOrderUpdateDto 수정할 발주 정보
	 * @return String
	 */	
	@PutMapping("/purchaseOrder")
	public ResponseEntity<String> updatePurchaseOrder(
			@RequestBody PurchaseOrderUpdateDto purchaseOrderUpdateDto) {
		try {
            this.purchaseOrderService.updatePurchaseOrder(purchaseOrderUpdateDto);
            return new ResponseEntity<>("성공적으로 수정되었습니다.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("발주 수정에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	


	/**
	 * 발주 목록 조회
	 * @param 
	 * @return List<PurchaseOrderListDto> 조회된 발주 목록
	 */
	@GetMapping("/api/order")
	public ResponseEntity<List<PurchaseOrderListDto>> getStockList(){
		try {
			List<PurchaseOrderListDto> purchaseOrderList = this.purchaseOrderService.getPurchaseOrderList();
            return new ResponseEntity<>(purchaseOrderList , HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}

	/**
	 * 발주 삭제
	 * @param Long 발주 번호
	 * @return boolean
	 */
	@DeleteMapping("/purchaseOrder")
	public ResponseEntity<String> deletePurchaseOrder(
			@RequestParam Long purchaseOrderNo){
		try {
			this.stockService.deleteStock(purchaseOrderNo);
			return new ResponseEntity<>("발주를 삭제하였습니다.", HttpStatus.OK);
		} catch(Exception e) {
        	System.out.println(e.getMessage());
			return new ResponseEntity<>("발주 삭제 살패 하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
	}

	
}
