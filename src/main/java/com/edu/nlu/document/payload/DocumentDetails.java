package com.edu.nlu.document.payload;

import com.edu.nlu.document.model.File;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@Getter
@Setter
@ToString
public class DocumentDetails {
    private Long documentId;
    private DocumentForm documentForm;
    private List<File> files;
}
