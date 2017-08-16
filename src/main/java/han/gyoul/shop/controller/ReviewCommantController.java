package han.gyoul.shop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import han.gyoul.shop.review.dto.CommentDTO;
import han.gyoul.shop.review.dto.Criteria;
import han.gyoul.shop.review.dto.PageMaker;
import han.gyoul.shop.review.service.CommentService;

@RestController
@SessionAttributes({"memberDTO","itemList"})
@RequestMapping("/replies")
public class ReviewCommantController {
	
	@Inject
	private CommentService coService;

	// ��۵��
		@RequestMapping(value = "", method = RequestMethod.POST)
		public ResponseEntity<String> register(@RequestBody CommentDTO commentDTO) {

			ResponseEntity<String> entity = null;
			try {
				coService.addComment(commentDTO);
				entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
			}
			return entity;
		}

		// �����ü���
		@RequestMapping(value = "/all/{review_no}", method = RequestMethod.GET)
		public ResponseEntity<List<CommentDTO>> list(@PathVariable("review_no") Integer review_no) {

			ResponseEntity<List<CommentDTO>> entity = null;
			try {
				entity = new ResponseEntity<>(coService.listComment(review_no), HttpStatus.OK);

			} catch (Exception e) {
				e.printStackTrace();
				entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}

			return entity;
		}

		// ��ۼ���
		@RequestMapping(value = "/{comment_no}", method = { RequestMethod.PUT, RequestMethod.PATCH })
		public ResponseEntity<String> update(@PathVariable("comment_no") Integer comment_no,
				@RequestBody CommentDTO commentDTO) {

			ResponseEntity<String> entity = null;
			try {
				commentDTO.setComment_no(comment_no);
				coService.modifyComment(commentDTO);

				entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
			}
			return entity;
		}

		// ��ۻ���
		@RequestMapping(value = "/{comment_no}", method = RequestMethod.DELETE)
		public ResponseEntity<String> remove(@PathVariable("comment_no") Integer comment_no) {

			ResponseEntity<String> entity = null;
			try {
				coService.removeComment(comment_no);
				entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
			}
			return entity;
		}

		// �������¡ó��
		@RequestMapping(value = "/{review_no}/{page}", method = RequestMethod.GET)
		public ResponseEntity<Map<String, Object>> listPage(
				@PathVariable("review_no") Integer review_no,
				@PathVariable("page") Integer page) {

			ResponseEntity<Map<String, Object>> entity = null;

			try {
				Criteria cri = new Criteria();
				cri.setPage(page);

				PageMaker pageMaker = new PageMaker();
				pageMaker.setCri(cri);

				Map<String, Object> map = new HashMap<String, Object>();
				List<CommentDTO> list = coService.listCommentPage(review_no, cri);

				map.put("list", list);

				int commentCount = coService.count(review_no);
				pageMaker.setTotalCount(commentCount);

				map.put("pageMaker", pageMaker);

				entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

			} catch (Exception e) {
				e.printStackTrace();
				entity = new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);
			}
			return entity;
		}
}
