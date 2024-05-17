package  ma.zs.stocky.ws.facade.etudiant.piecejointe;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.stocky.bean.core.piecejointe.Attachement;
import ma.zs.stocky.dao.criteria.core.piecejointe.AttachementCriteria;
import ma.zs.stocky.service.facade.etudiant.piecejointe.AttachementEtudiantService;
import ma.zs.stocky.ws.converter.piecejointe.AttachementConverter;
import ma.zs.stocky.ws.dto.piecejointe.AttachementDto;
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
@RequestMapping("/api/etudiant/attachement/")
public class AttachementRestEtudiant {




    @Operation(summary = "Finds a list of all attachements")
    @GetMapping("")
    public ResponseEntity<List<AttachementDto>> findAll() throws Exception {
        ResponseEntity<List<AttachementDto>> res = null;
        List<Attachement> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<AttachementDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a attachement by id")
    @GetMapping("id/{id}")
    public ResponseEntity<AttachementDto> findById(@PathVariable Long id) {
        Attachement t = service.findById(id);
        if (t != null) {
            converter.init(true);
            AttachementDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  attachement")
    @PostMapping("")
    public ResponseEntity<AttachementDto> save(@RequestBody AttachementDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            Attachement myT = converter.toItem(dto);
            Attachement t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                AttachementDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  attachement")
    @PutMapping("")
    public ResponseEntity<AttachementDto> update(@RequestBody AttachementDto dto) throws Exception {
        ResponseEntity<AttachementDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Attachement t = service.findById(dto.getId());
            converter.copy(dto,t);
            Attachement updated = service.update(t);
            AttachementDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of attachement")
    @PostMapping("multiple")
    public ResponseEntity<List<AttachementDto>> delete(@RequestBody List<AttachementDto> dtos) throws Exception {
        ResponseEntity<List<AttachementDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<Attachement> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified attachement")
    @DeleteMapping("")
    public ResponseEntity<AttachementDto> delete(@RequestBody AttachementDto dto) throws Exception {
		ResponseEntity<AttachementDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            Attachement t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified attachement")
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
    @Operation(summary = "Delete multiple attachements by ids")
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



    @Operation(summary = "Finds a attachement and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<AttachementDto> findWithAssociatedLists(@PathVariable Long id) {
        Attachement loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        AttachementDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds attachements by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<AttachementDto>> findByCriteria(@RequestBody AttachementCriteria criteria) throws Exception {
        ResponseEntity<List<AttachementDto>> res = null;
        List<Attachement> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<AttachementDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated attachements by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody AttachementCriteria criteria) throws Exception {
        List<Attachement> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<AttachementDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets attachement data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody AttachementCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<AttachementDto> findDtos(List<Attachement> list){
        converter.initObject(true);
        List<AttachementDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<AttachementDto> getDtoResponseEntity(AttachementDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private AttachementEtudiantService service;
    @Autowired private AttachementConverter converter;





}
