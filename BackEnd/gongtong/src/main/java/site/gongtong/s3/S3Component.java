package site.gongtong.s3;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class S3Component {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.s3.folder.memberFolder}")
    private String memberFolder;

    @Value("${cloud.aws.s3.folder.reviewFolder}")
    private String reviewFolder;

    @Value("${cloud.aws.s3.folder.articleFolder}")
    private String articleFolder;
}
