package com.example.shirotest.server.word;

import com.example.shirotest.mapper.UserMapper;
import org.apache.poi.wp.usermodel.HeaderFooterType;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STVerticalJc;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExportWord {
    private XWPFHelperTable xwpfHelperTable = null;
    private XWPFHelper xwpfHelper = null;
    public ExportWord() {
        xwpfHelperTable = new XWPFHelperTable();
        xwpfHelper = new XWPFHelper();
    }
    /**
     * 创建好文档的基本 标题，表格  段落等部分
     */
    public XWPFDocument createXWPFDocument(List<Map> list) {
        XWPFDocument doc = new XWPFDocument();
        for(Map tmp:list)
        {
            createTitleParagraph(doc);
            createTableParagraph(doc, Integer.parseInt(tmp.get("count").toString())+1, 4);
        }
        return doc;
    }
    /**
     * 创建表格的标题样式
     */
    public void createTitleParagraph(XWPFDocument document) {
        XWPFParagraph titleParagraph = document.createParagraph();    //新建一个标题段落对象（就是一段文字）
        titleParagraph.setAlignment(ParagraphAlignment.CENTER);//样式居中
        XWPFRun titleFun = titleParagraph.createRun();    //创建文本对象
//        titleFun.setText(titleName); //设置标题的名字
        titleFun.setBold(true); //加粗
        titleFun.setColor("000000");//设置颜色
        titleFun.setFontSize(25);    //字体大小
//        titleFun.setFontFamily("");//设置字体
        titleFun.addBreak();    //换行
    }
    public void createTitleParagraph2(XWPFDocument document) {
        XWPFParagraph titleParagraph = document.createParagraph();    //新建一个标题段落对象（就是一段文字）
    }
    /**
     * 设置表格
     */
    public void createTableParagraph(XWPFDocument document, int rows, int cols) {
//        xwpfHelperTable.createTable(xdoc, rowSize, cellSize, isSetColWidth, colWidths)
        XWPFTable infoTable = document.createTable(rows, cols);
        xwpfHelperTable.setTableWidthAndHAlign(infoTable, "9072", STJc.CENTER);
        //合并表格
//        xwpfHelperTable.mergeCellsHorizontal(infoTable, 1, 1, 5);
//        xwpfHelperTable.mergeCellsVertically(infoTable, 0, 3, 6);
        //设置表格样式
        List<XWPFTableRow> rowList = infoTable.getRows();
        for(int i = 0; i < rowList.size(); i++) {
            XWPFTableRow infoTableRow = rowList.get(i);
            List<XWPFTableCell> cellList = infoTableRow.getTableCells();
            for(int j = 0; j < cellList.size(); j++) {
                XWPFParagraph cellParagraph = cellList.get(j).getParagraphArray(0);
                cellParagraph.setAlignment(ParagraphAlignment.CENTER);
                XWPFRun cellParagraphRun = cellParagraph.createRun();
                cellParagraphRun.setFontSize(12);
                if(i % 2 != 0) {
                    cellParagraphRun.setBold(true);
                }
            }
        }
        xwpfHelperTable.setTableHeight(infoTable, 560, STVerticalJc.CENTER);
    }

    /**
     * 往表格中填充数据
     */
    @SuppressWarnings("unchecked")
    public void exportCheckWord(Map<String, Object> dataList, XWPFDocument document, String savePath) throws IOException {
        List<Map> count=(List<Map>)dataList.get("TITLE");
        int x=0;
        for(Map tmp:count)
        {
            int c=Integer.parseInt( tmp.get("count").toString() );
            XWPFParagraph paragraph = document.getParagraphArray(x++);
            XWPFRun titleFun = paragraph.getRuns().get(0);
            titleFun.setText(tmp.get("tablecnname").toString()+"("+tmp.get("tablename").toString()+")");

        }

        List< List<List<Object>> > tableData = (List< List<List<Object>> > ) dataList.get("TABLEDATA");
        x=0;
        for(List<List<Object>> tmp:tableData)
        {
            XWPFTable table = document.getTableArray(x++);
            fillTableData(table, tmp);
        }

        xwpfHelper.saveDocument(document, savePath);
    }
    /**
     * 往表格中填充数据
     */
    public void fillTableData(XWPFTable table, List<List<Object>> tableData) {
        List<XWPFTableRow> rowList = table.getRows();
        for(int i = 0; i < tableData.size(); i++) {
            List<Object> list = tableData.get(i);
            List<XWPFTableCell> cellList = rowList.get(i).getTableCells();
            for(int j = 0; j < list.size(); j++) {
                XWPFParagraph cellParagraph = cellList.get(j).getParagraphArray(0);
                XWPFRun cellParagraphRun = cellParagraph.getRuns().get(0);
                cellParagraphRun.setText(String.valueOf(list.get(j)));
            }
        }
    }


}



class TestExportWord {
    public static void main(String[] args) throws Exception {
//        ExportWord ew = new ExportWord();
//        XWPFDocument document = ew.createXWPFDocument(20);
//        List<List<Object>> list = new ArrayList<List<Object>>();
//
//        List<Object> tempList = new ArrayList<Object>();
//        tempList.add("姓名");
//        tempList.add("黄xx");
//        tempList.add("性别");
//        tempList.add("男");
//        tempList.add("出生日期");
//        tempList.add("2018-10-10");
//        list.add(tempList);
//        tempList = new ArrayList<Object>();
//        tempList.add("出生地");
//        tempList.add("江西");
//        tempList.add("名族");
//        tempList.add("汉");
//        tempList.add("婚否");
//        tempList.add("否");
//        list.add(tempList);
//
//        Map<String, Object> dataList = new HashMap<String, Object>();
//        dataList.put("TITLE", "个人体检表");
//        dataList.put("TABLEDATA", list);
//        ew.exportCheckWord(dataList, document, "E:/expWordTest.docx");
//        System.out.println("文档生成成功");
    }
}