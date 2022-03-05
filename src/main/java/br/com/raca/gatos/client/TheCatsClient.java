package br.com.raca.gatos.client;

import br.com.raca.gatos.model.rest.BreedResponse;
import br.com.raca.gatos.model.rest.ImagesResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "TheCatsApi", url = "${the-cats-api.url}")
public interface TheCatsClient {

    @GetMapping("v1/breeds")
    List<BreedResponse> getBreeds(@RequestParam String limit);

    @GetMapping("v1/images/search")
    List<ImagesResponse> getImagesSearchByBreedIdOrCategoryId(
            @RequestParam(name = "breed_ids") String idExternoRaca,
            @RequestParam(name = "category_ids") String idCategoria,
            @RequestParam(name = "limit") String limite,
            @RequestParam(name = "order", defaultValue = "desc") String ordenacao,
            @RequestHeader(name = "x-api-key") String apiKey);

    @GetMapping("v1/images")
    List<ImagesResponse> getImages(@RequestHeader(name = "x-api-key", defaultValue = "${the-cats-api.api-key}") String apiKey);

    @GetMapping("v1/images/search")
    List<ImagesResponse> getImagesSearchByBreedIds(
            @RequestParam(name = "breed_ids") List<String> idExternoRaca,
            @RequestParam(name = "limit") String limite,
            @RequestHeader(name = "x-api-key", defaultValue = "${the-cats-api.api-key}") String apiKey);
}
