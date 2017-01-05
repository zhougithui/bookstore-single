package org.bear.bookstore.web.custom;

import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 * 在计算机科学中，XSLT是 扩展样式表转换语言 的外语缩写，这是一种对XML（标准通用标记语言的子集）文档进行转化的语言，
 * XSLT中的T代表英语中的“转换”（Transformation）。它是XSL（eXtensible Stylesheet Language）规范的一部分
 * @author q
 *
 */
@Controller
public class XsltController {

    @RequestMapping("/xslt")
    public String home(Model model) throws Exception {

        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        Element root = document.createElement("wordList");

        List<String> words = Arrays.asList("Hello", "Spring", "Framework");
        for (String word : words) {
            Element wordNode = document.createElement("word");
            Text textNode = document.createTextNode(word);
            wordNode.appendChild(textNode);
            root.appendChild(wordNode);
        }

        model.addAttribute("wordList", root);
        return "home";
    }

}