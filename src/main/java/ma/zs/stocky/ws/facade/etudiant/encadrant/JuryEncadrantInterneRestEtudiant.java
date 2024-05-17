package  ma.zs.stocky.ws.facade.etudiant.encadrant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.stocky.bean.core.encadrant.JuryEncadrantInterne;
import ma.zs.stocky.dao.criteria.core.encadrant.JuryEncadrantInterneCriteria;
import ma.zs.stocky.service.facade.etudiant.encadrant.JuryEncadrantInterneEtudiantService;
import ma.zs.stocky.ws.converter.encadrant.JuryEncadrantInterneConverter;
import ma.zs.stocky.ws.dto.encadrant.JuryEncadrantInterneDto;
import ma.zs.stocky.zynerator.controller.AbstractController;
import ma.zs.stocky.zynerator.dto.AuditEntityDto;
import ma.zs.stocky.zynerator.util.PaginatedList;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zs.stocky.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zs.stocky.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/etudiant/juryEncadrantInterne/")
public class JuryEncadrantInterneRestEtudiant {




    @Operation(summary = "Finds a list of all juryEncadrantInternes")
    @GetMapping("")
    public ResponseEntity<List<JuryEncadrantInterneDto>> findAll() throws Exception {
        ResponseEntity<List<JuryEncadrantInterneDto>> res = null;
        List<JuryEncadrantInterne> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<JuryEncadrantInterneDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a juryEncadrantInterne by id")
    @GetMapping("id/{id}")
    public ResponseEntity<JuryEncadrantInterneDto> findById(@PathVariable Long id) {
        JuryEncadrantInterne t = service.findById(id);
        if (t != null) {
            converter.init(true);
            JuryEncadrantInterneDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  juryEncadrantInterne")
    @PostMapping("")
    public ResponseEntity<JuryEncadrantInterneDto> save(@RequestBody JuryEncadrantInterneDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            JuryEncadrantInterne myT = converter.toItem(dto);
            JuryEncadrantInterne t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                JuryEncadrantInterneDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  juryEncadrantInterne")
    @PutMapping("")
    public ResponseEntity<JuryEncadrantInterneDto> update(@RequestBody JuryEncadrantInterneDto dto) throws Exception {
        ResponseEntity<JuryEncadrantInterneDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            JuryEncadrantInterne t = service.findById(dto.getId());
            converter.copy(dto,t);
            JuryEncadrantInterne updated = service.update(t);
            JuryEncadrantInterneDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of juryEncadrantInterne")
    @PostMapping("multiple")
    public ResponseEntity<List<JuryEncadrantInterneDto>> delete(@RequestBody List<JuryEncadrantInterneDto> dtos) throws Exception {
        ResponseEntity<List<JuryEncadrantInterneDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<JuryEncadrantInterne> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified juryEncadrantInterne")
    @DeleteMapping("")
    public ResponseEntity<JuryEncadrantInterneDto> delete(@RequestBody JuryEncadrantInterneDto dto) throws Exception {
		ResponseEntity<JuryEncadrantInterneDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            JuryEncadrantInterne t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified juryEncadrantInterne")
    @DeleteMapping("id/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) throws Exception {
        ResponseEntity<Long> res;
        HttpStatus status = HttpStatus.PRECONDITION_FAILED;
        if (id != null) {
            boolean resultDelete = service.deleteById(id);
            if (resultDelete) {
                status = HttpStatus.OK;
            }
        }
        res = new ResponseEntity<>(id, status);
        return res;
    }
    @Operation(summary = "Delete multiple juryEncadrantInternes by ids")
    @DeleteMapping("multiple/id")
    public ResponseEntity<List<Long>> deleteByIdIn(@RequestBody List<Long> ids) throws Exception {
        ResponseEntity<List<Long>> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (ids != null) {
            service.deleteByIdIn(ids);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(ids, status);
        return res;
     }



    @Operation(summary = "Finds a juryEncadrantInterne and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<JuryEncadrantInterneDto> findWithAssociatedLists(@PathVariable Long id) {
        JuryEncadrantInterne loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        JuryEncadrantInterneDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds juryEncadrantInternes by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<JuryEncadrantInterneDto>> findByCriteria(@RequestBody JuryEncadrantInterneCriteria criteria) throws Exception {
        ResponseEntity<List<JuryEncadrantInterneDto>> res = null;
        List<JuryEncadrantInterne> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<JuryEncadrantInterneDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated juryEncadrantInternes by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody JuryEncadrantInterneCriteria criteria) throws Exception {
        List<JuryEncadrantInterne> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<JuryEncadrantInterneDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets juryEncadrantInterne data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody JuryEncadrantInterneCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<JuryEncadrantInterneDto> findDtos(List<JuryEncadrantInterne> list){
        converter.initObject(true);
        List<JuryEncadrantInterneDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<JuryEncadrantInterneDto> getDtoResponseEntity(JuryEncadrantInterneDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private JuryEncadrantInterneEtudiantService service;
    @Autowired private JuryEncadrantInterneConverter converter;





}
