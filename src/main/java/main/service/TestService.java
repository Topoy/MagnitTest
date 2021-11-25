package main.service;

import main.api.XMLParser;
import main.model.Test;
import main.repository.TestRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestService {

    private final TestRepository testRepository;

    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public List<Test> getTestContent() {
        return testRepository.findAll();
    }

    public List<Test> fillTestContent(int fieldsNumber) {
        if (testRepository.count() != 0) {
            testRepository.deleteAll();
        }
        List<Test> newTestContent = new ArrayList<>();
        if (fieldsNumber <= 0) {
            return newTestContent;
        }
        for (int i = 0; i < fieldsNumber; i++) {
            Test test = new Test();
            test.setField(i + 1);
            newTestContent.add(test);
        }
        testRepository.saveAll(newTestContent);
        return newTestContent;
    }

    public void createXML() {
        List<Test> testContent = testRepository.findAll();
        XMLParser.createXML(testContent, "src/main/resources/1.xml");
    }

    public void transformXML() {
        String xsltPath = "src/main/resources/transformer.xsl";
        String xmlPath = "src/main/resources/1.xml";
        String resultPath = "src/main/resources/2.xml";
        XMLParser.transformXML(xsltPath, xmlPath, resultPath);
    }

    public Long getFieldValuesSum() {
        return XMLParser.getFieldValuesSum("src/main/resources/2.xml");
    }

}
