package kr.co.test.week2board;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class SwaggerConfig {
    private static final String API_NAME = "Study API";
    private static final String API_VERSION = "0.0.1";
    private static final String API_DESCRIPTION = "Study API 명세서";

    @Bean
    public GroupedOpenApi boardGroupedOpenApi() {
        return GroupedOpenApi
            .builder()
            .group("board") // group 설정 (API들을 그룹화시켜 그룹에 속한 API들만 확인할 수 있도록 도와줌)
            .pathsToMatch("/api/**") // group에 포함될 API endpoint 경로
            .addOpenApiCustomizer(
        openApi ->
                openApi
                    .setInfo(
                        new Info()
                            .title("board api") // API 제목
                            .description("게시판 업무 처리를 위한 API") // API 설명
                            .version("1.0.0") // API 버전
                    )
            )
            .build();
    }

    /**
     * Swagger springdoc-ui 구성 파일
     */
    @Configuration
    public class OpenApiConfig {
        @Bean
        public OpenAPI openAPI() {
            Info info = new Info()
                    .title("데모 프로젝트 API Document")
                    .version("v0.0.1")
                    .description("데모 프로젝트의 API 명세서입니다.");
            return new OpenAPI()
                    .components(new Components())
                    .info(info);
        }
    }

}
