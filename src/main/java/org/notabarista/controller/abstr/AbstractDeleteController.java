package org.notabarista.controller.abstr;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.time.StopWatch;
import org.notabarista.dto.AbstractDTO;
import org.notabarista.entity.AbstractEntity;
import org.notabarista.entity.response.Response;
import org.notabarista.entity.response.ResponseStatus;
import org.notabarista.exception.AbstractNotabaristaException;
import org.notabarista.service.abstr.IDeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.NonNull;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public abstract class AbstractDeleteController<T extends AbstractEntity, U extends AbstractDTO> extends AbstractWriteController<T, U> {

	@Autowired
	private IDeleteService<T, U> service;

	@CrossOrigin
	@DeleteMapping(value = "/delete/{id}", produces = "application/json")
	@ResponseBody
	public ResponseEntity<Response<U>> deleteById(@NonNull @PathVariable Integer id, HttpServletRequest request,
			HttpServletResponse response) throws AbstractNotabaristaException {
		StopWatch watch = new StopWatch();
		watch.start();
		if (log.isInfoEnabled()) {
			log.info("Delete entity");
		}
		if (log.isDebugEnabled()) {
			log.debug("Delete entity:" + id);
		}
		
		service.deleteById(id);
		
		watch.stop();
		return new ResponseEntity<>(new Response<U>(ResponseStatus.SUCCESS, watch.getTime(), null, 0, 0, 0, 0, ""),
				HttpStatus.OK);
	}

	@CrossOrigin
	@DeleteMapping(value = "/deleteByIds", produces = "application/json")
	@ResponseBody
	public ResponseEntity<Response<U>> deleteByIds(@NonNull @RequestBody List<Integer> ids, HttpServletRequest request,
			HttpServletResponse response) throws AbstractNotabaristaException {
		StopWatch watch = new StopWatch();
		watch.start();
		if (log.isInfoEnabled()) {
			log.info("Delete by ids");
		}
		if (log.isDebugEnabled()) {
			log.debug("Delete by ids:" + ids);
		}

		service.deleteByIds(ids);
		
		watch.stop();
		return new ResponseEntity<>(new Response<U>(ResponseStatus.SUCCESS, watch.getTime(), null, 0, 0, 0, 0, ""),
				HttpStatus.OK);
	}

	@CrossOrigin
	@DeleteMapping(value = "/delete", produces = "application/json")
	@ResponseBody
	public ResponseEntity<Response<U>> delete(@NonNull @RequestBody U dto, HttpServletRequest request,
			HttpServletResponse response) throws AbstractNotabaristaException {
		StopWatch watch = new StopWatch();
		watch.start();
		if (log.isInfoEnabled()) {
			log.info("Delete dto");
		}
		if (log.isDebugEnabled()) {
			log.debug("Delete dto:" + dto);
		}

		service.delete(dto);
		
		watch.stop();
		return new ResponseEntity<>(new Response<U>(ResponseStatus.SUCCESS, watch.getTime(), null, 0, 0, 0, 0, ""),
				HttpStatus.OK);
	}

}
