package  ma.zs.stocky.ws.facade.etudiant.jury;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.stocky.bean.core.jury.Jury;
import ma.zs.stocky.dao.criteria.core.jury.JuryCriteria;
import ma.zs.stocky.service.facade.etudiant.jury.JuryEtudiantService;
import ma.zs.stocky.ws.converter.jury.JuryConverter;
import ma.zs.stocky.ws.dto.jury.JuryDto;
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
@RequestMapping("/api/etudiant/jury/")
public class JuryRestEtudiant {




    @Operation(summary = "Finds a list of all jurys")
    @GetMapping("")
    public ResponseEntity<List<JuryDto>> findAll() throws Exception {
        ResponseEntity<List<JuryDto>> res = null;
        List<Jury> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
        List<JuryDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all jurys")
    @GetMapping("optimized")
    public ResponseEntity<List<JuryDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<JuryDto>> res = null;
        List<Jury> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
        List<JuryDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a jury by id")
    @GetMapping("id/{id}")
    public ResponseEntity<JuryDto> findById(@PathVariable Long id) {
        Jury t = service.findById(id);
        if (t != null) {
            converter.init(true);
            JuryDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a jury by ref")
    @GetMapping("ref/{ref}")
    public ResponseEntity<JuryDto> findByRef(@PathVariable String ref) {
	    Jury t = service.findByReferenceEntity(new Jury(ref));
        if (t != null) {
            converter.init(true);
            JuryDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  jury")
    @PostMapping("")
    public ResponseEntity<JuryDto> save(@RequestBody JuryDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            Jury myT = converter.toItem(dto);
            Jury t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                JuryDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  jury")
    @PutMapping("")
    public ResponseEntity<JuryDto> update(@RequestBody JuryDto dto) throws Exception {
        ResponseEntity<JuryDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Jury t = service.findById(dto.getId());
            converter.copy(dto,t);
            Jury updated = service.update(t);
            JuryDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of jury")
    @PostMapping("multiple")
    public ResponseEntity<List<JuryDto>> delete(@RequestBody List<JuryDto> dtos) throws Exception {
        ResponseEntity<List<JuryDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<Jury> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified jury")
    @DeleteMapping("")
    public ResponseEntity<JuryDto> delete(@RequestBody JuryDto dto) throws Exception {
		ResponseEntity<JuryDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            Jury t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified jury")
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
    @Operation(summary = "Delete multiple jurys by ids")
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



    @Operation(summary = "Finds a jury and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<JuryDto> findWithAssociatedLists(@PathVariable Long id) {
        Jury loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        JuryDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds jurys by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<JuryDto>> findByCriteria(@RequestBody JuryCriteria criteria) throws Exception {
        ResponseEntity<List<JuryDto>> res = null;
        List<Jury> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
        List<JuryDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated jurys by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody JuryCriteria criteria) throws Exception {
        List<Jury> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initList(false);
        List<JuryDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets jury data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody JuryCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<JuryDto> findDtos(List<Jury> list){
        converter.initList(false);
        List<JuryDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<JuryDto> getDtoResponseEntity(JuryDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private JuryEtudiantService service;
    @Autowired private JuryConverter converter;





}
