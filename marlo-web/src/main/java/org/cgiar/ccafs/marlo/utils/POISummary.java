/*****************************************************************
 * This file is part of Managing Agricultural Research for Learning &
 * Outcomes Platform (MARLO).
 * MARLO is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * at your option) any later version.
 * MARLO is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with MARLO. If not, see <http://www.gnu.org/licenses/>.
 *****************************************************************/

package org.cgiar.ccafs.marlo.utils;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.Borders;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.TOC;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRelation;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHyperlink;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtBlock;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTVMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STUnderline;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class POISummary {

  // LOG
  private static final Logger LOG = LoggerFactory.getLogger(POISummary.class);


  private final static String FONT_TYPE = "Calibri Light";
  private final static String TITLE_FONT_COLOR = "3366CC";
  private final static String TEXT_FONT_COLOR = "000000";
  private final static Integer TABLE_TEXT_FONT_SIZE = 10;
  private static String TABLE_HEADER_FONT_COLOR = "FFF2CC";
  private int count = 0;

  private void addParagraphTextBreak(XWPFRun paragraphRun, String text) {
    if (text.contains("\n")) {
      String[] lines = text.split("\n");
      paragraphRun.setText(lines[0], 0); // set first line into XWPFRun
      for (int i = 1; i < lines.length; i++) {
        // add break and insert new text
        paragraphRun.addBreak();
        paragraphRun.setText(lines[i]);
      }
    } else {
      paragraphRun.setText(text, 0);
    }
  }

  public void createTOC(XWPFDocument document) {
    // Create table of contents
    CTSdtBlock block = document.getDocument().getBody().addNewSdt();
    TOC toc = new TOC(block);
    for (XWPFParagraph par : document.getParagraphs()) {
      String parStyle = par.getStyle();
      if ((parStyle != null) && (parStyle.startsWith("Heading"))) {
        try {
          int level = Integer.valueOf(parStyle.substring("Heading".length())).intValue();
          toc.addRow(level, par.getText(), 1, "112723803");
        } catch (NumberFormatException e) {
          e.printStackTrace();
        }
      }
    }
  }

  /**
   * Footer title
   * 
   * @param document
   * @param text
   * @throws IOException
   */
  public void pageFooter(XWPFDocument document, String text) throws IOException {
    CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
    XWPFHeaderFooterPolicy policy = new XWPFHeaderFooterPolicy(document, sectPr);
    CTP ctpFooter = CTP.Factory.newInstance();
    CTR ctrFooter = ctpFooter.addNewR();
    CTText ctFooter = ctrFooter.addNewT();
    ctFooter.setStringValue(text);
    XWPFParagraph footerParagraph = new XWPFParagraph(ctpFooter, document);
    footerParagraph.setAlignment(ParagraphAlignment.LEFT);
    XWPFParagraph[] parsFooter = new XWPFParagraph[1];
    parsFooter[0] = footerParagraph;
    policy.createFooter(XWPFHeaderFooterPolicy.DEFAULT, parsFooter);
  }

  /**
   * Header title
   * 
   * @param document
   * @param text
   * @throws IOException
   */
  public void pageHeader(XWPFDocument document, String text) throws IOException {
    CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
    XWPFHeaderFooterPolicy policy = new XWPFHeaderFooterPolicy(document, sectPr);
    CTP ctpHeader = CTP.Factory.newInstance();
    CTR ctrHeader = ctpHeader.addNewR();
    CTText ctHeader = ctrHeader.addNewT();
    ctHeader.setStringValue(text);
    XWPFParagraph headerParagraph = new XWPFParagraph(ctpHeader, document);
    headerParagraph.setAlignment(ParagraphAlignment.RIGHT);
    XWPFParagraph[] parsHeader = new XWPFParagraph[1];
    parsHeader[0] = headerParagraph;
    policy.createHeader(XWPFHeaderFooterPolicy.DEFAULT, parsHeader);
  }

  public void pageLeftHeader(XWPFDocument document, String text) throws IOException {
    CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
    XWPFHeaderFooterPolicy policy = new XWPFHeaderFooterPolicy(document, sectPr);
    CTP ctpHeader = CTP.Factory.newInstance();
    CTR ctrHeader = ctpHeader.addNewR();
    CTText ctHeader = ctrHeader.addNewT();
    ctHeader.setStringValue(text);
    XWPFParagraph headerParagraph = new XWPFParagraph(ctpHeader, document);
    headerParagraph.setAlignment(ParagraphAlignment.LEFT);
    XWPFParagraph[] parsHeader = new XWPFParagraph[1];
    parsHeader[0] = headerParagraph;
    policy.createHeader(XWPFHeaderFooterPolicy.DEFAULT, parsHeader);
  }


  public void tableA1AnnualReportStyle(XWPFTable table) {
    /* horizontal merge, From format tables A1 */

    for (int x = 0; x < table.getNumberOfRows(); x++) {
      XWPFTableRow row = table.getRow(x);
      for (int y = 0; y < row.getTableCells().size(); y++) {
        XWPFTableCell cell = row.getCell(y);
        CTTblWidth cellWidth = cell.getCTTc().addNewTcPr().addNewTcW();

        CTTcPr pr = cell.getCTTc().addNewTcPr();
        // pr.addNewNoWrap();
        cellWidth.setW(BigInteger.valueOf(100));
      }
    }
  }

  public void tableAStyle(XWPFTable table) {
    /* Horizontal merge, From format tables A */
    CTVMerge vmerge = CTVMerge.Factory.newInstance();
    CTVMerge vmerge1 = CTVMerge.Factory.newInstance();

    for (int x = 0; x < table.getNumberOfRows(); x++) {
      if (x > 0) {
        XWPFTableRow row = table.getRow(x);
        for (int y = 0; y < 2; y++) {
          XWPFTableCell cell = row.getCell(y);

          if (cell.getCTTc() == null) {
            ((CTTc) cell).addNewTcPr();
          }

          if (cell.getCTTc().getTcPr() == null) {
            cell.getCTTc().addNewTcPr();
          }
          if (x == 1 && !(cell.getText().trim().length() > 0)) {
            break;
          }
          if (cell.getText().trim().length() > 0) {
            if (y == 0) {
              cell.getCTTc().getTcPr().addNewTcW().setW(BigInteger.valueOf(1500));
            }
            vmerge.setVal(STMerge.RESTART);
            cell.getCTTc().getTcPr().setVMerge(vmerge);
          } else {
            if (y == 0) {
              cell.getCTTc().getTcPr().addNewTcW().setW(BigInteger.valueOf(1500));
            }
            vmerge1.setVal(STMerge.CONTINUE);
            cell.getCTTc().getTcPr().setVMerge(vmerge1);
          }
        }

      }
    }
  }

  public void tableBAnnualReportStyle(XWPFTable table) {
    /* Horizontal merge, From format tables B */
    CTVMerge vmerge = CTVMerge.Factory.newInstance();
    CTVMerge vmerge1 = CTVMerge.Factory.newInstance();

    for (int x = 0; x < table.getNumberOfRows(); x++) {
      if (x > 0) {
        XWPFTableRow row = table.getRow(x);
        for (int y = 0; y < 2; y++) {
          XWPFTableCell cell = row.getCell(y);

          if (cell.getCTTc() == null) {
            ((CTTc) cell).addNewTcPr();
          }

          if (cell.getCTTc().getTcPr() == null) {
            cell.getCTTc().addNewTcPr();
          }
          if (x == 1 && !(cell.getText().trim().length() > 0)) {
            break;
          }
          if (cell.getText().trim().length() > 0) {
            if (y == 0) {
              cell.getCTTc().getTcPr().addNewTcW().setW(BigInteger.valueOf(1500));
            }
            vmerge.setVal(STMerge.RESTART);
            cell.getCTTc().getTcPr().setVMerge(vmerge);
          } else {
            if (y == 0) {
              cell.getCTTc().getTcPr().addNewTcW().setW(BigInteger.valueOf(1500));
            }
            vmerge1.setVal(STMerge.CONTINUE);
            cell.getCTTc().getTcPr().setVMerge(vmerge1);
          }
        }

      }
    }
  }


  public void tableCStyle(XWPFTable table) {
    /* Vertical merge, From format tables C */
    CTVMerge vmerge = CTVMerge.Factory.newInstance();
    CTVMerge vmerge1 = CTVMerge.Factory.newInstance();


    for (int x = 0; x < table.getNumberOfRows(); x++) {
      if (x > 0) {
        XWPFTableRow row = table.getRow(x);
        XWPFTableCell cell = row.getCell(row.getTableCells().size() - 1);

        if (cell.getCTTc() == null) {
          ((CTTc) cell).addNewTcPr();
        }

        if (cell.getCTTc().getTcPr() == null) {
          cell.getCTTc().addNewTcPr();
        }
        if (x == 1 && !(cell.getText().trim().length() > 0)) {
          break;
        }
        if (cell.getText().trim().length() > 0) {
          vmerge.setVal(STMerge.RESTART);
          cell.getCTTc().getTcPr().setVMerge(vmerge);
        } else {
          vmerge1.setVal(STMerge.CONTINUE);
          cell.getCTTc().getTcPr().setVMerge(vmerge1);
        }
      }
    }
  }

  public void tableD1AnnualReportStyle(XWPFTable table) {
    /* Horizontal merge, From format tables D1 Annual report */
    CTVMerge vmerge = CTVMerge.Factory.newInstance();
    CTVMerge vmerge1 = CTVMerge.Factory.newInstance();

    for (int x = 0; x < table.getNumberOfRows(); x++) {
      if (x > 0) {
        XWPFTableRow row = table.getRow(x);
        for (int y = 0; y < 2; y++) {
          XWPFTableCell cell = row.getCell(y);

          if (cell.getCTTc() == null) {
            ((CTTc) cell).addNewTcPr();
          }

          if (cell.getCTTc().getTcPr() == null) {
            cell.getCTTc().addNewTcPr();
          }
          if (x == 1 && !(cell.getText().trim().length() > 0)) {
            break;
          }
          if (cell.getText().trim().length() > 0) {
            if (y == 0) {
              cell.getCTTc().getTcPr().addNewTcW().setW(BigInteger.valueOf(1500));
            }
            vmerge.setVal(STMerge.RESTART);
            cell.getCTTc().getTcPr().setVMerge(vmerge);
          } else {
            if (y == 0) {
              cell.getCTTc().getTcPr().addNewTcW().setW(BigInteger.valueOf(1500));
            }
            vmerge1.setVal(STMerge.CONTINUE);
            cell.getCTTc().getTcPr().setVMerge(vmerge1);
          }
        }

      }
    }
  }

  public void tableEStyle(XWPFTable table) {
    /* Horizontal merge, From format tables E */
    CTHMerge hMerge = CTHMerge.Factory.newInstance();
    CTHMerge hMerge1 = CTHMerge.Factory.newInstance();


    XWPFTableRow row = table.getRow(0);
    int numberOfCell = row.getTableCells().size();
    for (int y = 0; y < numberOfCell - 1; y++) {
      XWPFTableCell cell = row.getCell(y);

      if (cell.getCTTc() == null) {
        ((CTTc) cell).addNewTcPr();
      }

      if (cell.getCTTc().getTcPr() == null) {
        cell.getCTTc().addNewTcPr();
      }
      if (y > 0 && y < numberOfCell) {
        if (cell.getText().trim().length() > 0) {
          hMerge.setVal(STMerge.RESTART);
          cell.getCTTc().getTcPr().setHMerge(hMerge);
        } else {
          hMerge1.setVal(STMerge.CONTINUE);
          cell.getCTTc().getTcPr().setHMerge(hMerge1);
        }
      }
    }

    for (int x = 0; x < table.getNumberOfRows(); x++) {
      if (x > 1) {
        XWPFTableRow rowCom = table.getRow(x);
        XWPFTableCell cell = rowCom.getCell(6);

        if (cell.getCTTc() == null) {
          ((CTTc) cell).addNewTcPr();
        }

        if (cell.getCTTc().getTcPr() == null) {
          cell.getCTTc().addNewTcPr();
        }

        cell.getCTTc().getTcPr().addNewTcW().setW(BigInteger.valueOf(5000));

      }
    }
  }

  public void tableFStyle(XWPFTable table) {
    for (int x = 0; x < table.getNumberOfRows(); x++) {
      if (x > 0) {
        XWPFTableRow row = table.getRow(x);
        XWPFTableCell cell = row.getCell(2);

        if (cell.getCTTc() == null) {
          ((CTTc) cell).addNewTcPr();
        }

        if (cell.getCTTc().getTcPr() == null) {
          cell.getCTTc().addNewTcPr();
        }

        cell.getCTTc().getTcPr().addNewTcW().setW(BigInteger.valueOf(7000));

      }
    }
  }

  public void tableGStyle(XWPFTable table) {
    for (int x = 0; x < table.getNumberOfRows(); x++) {
      XWPFTableRow row = table.getRow(x);
      for (int y = 0; y < row.getTableCells().size(); y++) {
        XWPFTableCell cell = row.getCell(y);
        if (cell.getCTTc() == null) {
          ((CTTc) cell).addNewTcPr();
        }

        if (cell.getCTTc().getTcPr() == null) {
          cell.getCTTc().addNewTcPr();
        }

        cell.getCTTc().getTcPr().addNewTcW().setW(BigInteger.valueOf(5000));
      }
    }
  }

  public void tableIAnnualReportStyle(XWPFTable table) {
    /* horizontal merge, From format tables I */

    for (int x = 0; x < table.getNumberOfRows(); x++) {
      XWPFTableRow row = table.getRow(x);
      for (int y = 0; y < row.getTableCells().size(); y++) {
        XWPFTableCell cell = row.getCell(y);
        CTTblWidth cellWidth = cell.getCTTc().addNewTcPr().addNewTcW();

        CTTcPr pr = cell.getCTTc().addNewTcPr();
        // pr.addNewNoWrap();
        cellWidth.setW(BigInteger.valueOf(100));
      }
    }
  }

  public void tableJAnnualReportStyle(XWPFTable table) {
    /* Horizontal merge, From format tables J */
    CTHMerge hMerge = CTHMerge.Factory.newInstance();
    CTHMerge hMerge1 = CTHMerge.Factory.newInstance();


    XWPFTableRow row = table.getRow(0);
    int numberOfCell = row.getTableCells().size();
    for (int y = 0; y < numberOfCell; y++) {
      XWPFTableCell cell = row.getCell(y);

      if (cell.getCTTc() == null) {
        ((CTTc) cell).addNewTcPr();
      }

      if (cell.getCTTc().getTcPr() == null) {
        cell.getCTTc().addNewTcPr();
      }
      if (y > 0 && y <= numberOfCell) {
        if (cell.getText().trim().length() > 0) {
          hMerge.setVal(STMerge.RESTART);
          cell.getCTTc().getTcPr().setHMerge(hMerge);
        } else {
          hMerge1.setVal(STMerge.CONTINUE);
          cell.getCTTc().getTcPr().setHMerge(hMerge1);
        }
      }
    }

    for (int x = 0; x < table.getNumberOfRows(); x++) {
      if (x > 1) {
        XWPFTableRow rowCom = table.getRow(x);
        XWPFTableCell cell = rowCom.getCell(6);

        if (cell.getCTTc() == null) {
          ((CTTc) cell).addNewTcPr();
        }

        if (cell.getCTTc().getTcPr() == null) {
          cell.getCTTc().addNewTcPr();
        }

        cell.getCTTc().getTcPr().addNewTcW().setW(BigInteger.valueOf(5000));

      }
    }
  }

  /**
   * Head 1 Title
   * 
   * @param h1
   * @param text
   */
  public void textHead1Title(XWPFParagraph h1, String text) {
    h1.setAlignment(ParagraphAlignment.BOTH);
    XWPFRun h1Run = h1.createRun();
    this.addParagraphTextBreak(h1Run, text);
    h1Run.setColor(TITLE_FONT_COLOR);
    h1Run.setBold(true);
    h1Run.setFontFamily(FONT_TYPE);
    h1Run.setFontSize(16);
  }

  public void textHead1TitleFontCalibri(XWPFParagraph h1, String text) {
    h1.setAlignment(ParagraphAlignment.BOTH);
    XWPFRun h1Run = h1.createRun();
    this.addParagraphTextBreak(h1Run, text);
    h1Run.setColor(TITLE_FONT_COLOR);
    h1Run.setBold(true);
    h1Run.setFontFamily("Calibri");
    h1Run.setFontSize(16);
  }


  public void textHead1TitleLightBlue(XWPFParagraph h1, String text) {
    h1.setAlignment(ParagraphAlignment.BOTH);
    XWPFRun h1Run = h1.createRun();
    this.addParagraphTextBreak(h1Run, text);
    h1Run.setColor("5B9BD5");
    h1Run.setBold(true);
    h1Run.setFontFamily("Calibri");
    h1Run.setFontSize(13);
  }

  public void textHead2Title(XWPFParagraph h2, String text) {
    h2.setAlignment(ParagraphAlignment.BOTH);
    XWPFRun h2Run = h2.createRun();
    this.addParagraphTextBreak(h2Run, text);
    h2Run.setColor(TITLE_FONT_COLOR);
    h2Run.setBold(true);
    h2Run.setFontFamily(FONT_TYPE);
    h2Run.setFontSize(14);
  }

  public void textHead3Title(XWPFParagraph h2, String text) {
    h2.setAlignment(ParagraphAlignment.BOTH);
    XWPFRun h2Run = h2.createRun();
    this.addParagraphTextBreak(h2Run, text);
    h2Run.setColor(TITLE_FONT_COLOR);
    h2Run.setBold(true);
    h2Run.setFontFamily(FONT_TYPE);
    h2Run.setFontSize(12);
  }

  public void textHeadCoverTitle(XWPFParagraph h1, String text) {
    h1.setAlignment(ParagraphAlignment.CENTER);
    XWPFRun h1Run = h1.createRun();
    this.addParagraphTextBreak(h1Run, text);
    h1Run.setColor(TEXT_FONT_COLOR);
    h1Run.setBold(false);
    h1Run.setFontFamily(FONT_TYPE);
    h1Run.setFontSize(26);
  }

  public void textHeadPrincipalTitle(XWPFParagraph h1, String text) {
    h1.setAlignment(ParagraphAlignment.LEFT);
    XWPFRun h1Run = h1.createRun();
    this.addParagraphTextBreak(h1Run, text);
    h1Run.setColor("323E4F");
    h1Run.setBold(false);
    h1Run.setFontFamily("Calibri");
    h1Run.setFontSize(26);
    h1.setBorderBottom(Borders.SINGLE);
  }

  public void textHyperlink(String url, String text, XWPFParagraph paragraph) {

    // Add the link as External relationship
    String id = paragraph.getDocument().getPackagePart()
      .addExternalRelationship(url, XWPFRelation.HYPERLINK.getRelation()).getId();

    // Append the link and bind it to the relationship
    CTHyperlink cLink = paragraph.getCTP().addNewHyperlink();
    cLink.setId(id);

    // // Create the linked text
    CTText ctText = CTText.Factory.newInstance();
    ctText.setStringValue(text);

    CTR ctr = CTR.Factory.newInstance();
    ctr.setTArray(new CTText[] {ctText});
    ctr.addNewRPr().addNewColor().setVal("0000FF");
    ctr.addNewRPr().addNewU().setVal(STUnderline.SINGLE);
    ctr.addNewRPr().addNewRFonts().setAscii(FONT_TYPE);
    // Insert the linked text into the link
    cLink.setRArray(new CTR[] {ctr});

  }

  public void textLineBreak(XWPFDocument document, int breakNumber) {
    for (int i = 0; i < breakNumber; i++) {
      document.createParagraph();
    }
  }

  public void textNotes(XWPFParagraph paragraph, String text) {
    paragraph.setAlignment(ParagraphAlignment.BOTH);
    XWPFRun paragraphRun = paragraph.createRun();
    this.addParagraphTextBreak(paragraphRun, text);
    paragraphRun.setColor(TEXT_FONT_COLOR);
    paragraphRun.setBold(false);
    paragraphRun.setFontFamily(FONT_TYPE);
    paragraphRun.setFontSize(10);
  }

  public void textParagraph(XWPFParagraph paragraph, String text) {
    paragraph.setAlignment(ParagraphAlignment.BOTH);
    XWPFRun paragraphRun = paragraph.createRun();
    this.addParagraphTextBreak(paragraphRun, text);
    paragraphRun.setColor(TEXT_FONT_COLOR);
    paragraphRun.setBold(false);
    paragraphRun.setFontFamily(FONT_TYPE);
    paragraphRun.setFontSize(11);
  }

  public void textParagraphFontCalibri(XWPFParagraph paragraph, String text) {
    paragraph.setAlignment(ParagraphAlignment.BOTH);
    XWPFRun paragraphRun = paragraph.createRun();
    this.addParagraphTextBreak(paragraphRun, text);
    paragraphRun.setColor(TEXT_FONT_COLOR);
    paragraphRun.setBold(false);
    paragraphRun.setFontFamily("Calibri");
    paragraphRun.setFontSize(11);
  }

  public void textParagraphItalicLightBlue(XWPFParagraph paragraph, String text) {
    paragraph.setAlignment(ParagraphAlignment.BOTH);
    XWPFRun paragraphRun = paragraph.createRun();
    this.addParagraphTextBreak(paragraphRun, text);
    paragraphRun.setColor("5B9BD5");
    paragraphRun.setBold(false);
    paragraphRun.setItalic(true);
    paragraphRun.setFontFamily("Calibri");
    paragraphRun.setFontSize(12);
  }

  public void textTable(XWPFDocument document, List<List<POIField>> sHeaders, List<List<POIField>> sData,
    Boolean highlightFirstColumn, String tableType) {
    XWPFTable table = document.createTable();
    int record = 0;
    int headerIndex = 0;
    for (List<POIField> poiParameters : sHeaders) {

      // Setting the Header
      XWPFTableRow tableRowHeader;
      if (headerIndex == 0) {
        tableRowHeader = table.getRow(0);
      } else {
        tableRowHeader = table.createRow();
      }
      for (POIField poiParameter : poiParameters) {

        // Condition for table b cell color in fields 5 and 6
        if (tableType.equals("tableBAnnualReport") && (record == 4 || record == 5)) {
          TABLE_HEADER_FONT_COLOR = "DEEAF6";
        } else {
          TABLE_HEADER_FONT_COLOR = "FFF2CC";
        }

        if (headerIndex == 0) {
          if (record == 0) {
            XWPFParagraph paragraph = tableRowHeader.getCell(0).addParagraph();
            paragraph.setAlignment(poiParameter.getAlignment());
            XWPFRun paragraphRun = paragraph.createRun();
            this.addParagraphTextBreak(paragraphRun, poiParameter.getText());
            paragraphRun.setColor(TEXT_FONT_COLOR);
            if (poiParameter.getBold() != null) {
              paragraphRun.setBold(poiParameter.getBold());
            } else {
              paragraphRun.setBold(true);
            }
            paragraphRun.setFontFamily(FONT_TYPE);
            paragraphRun.setFontSize(TABLE_TEXT_FONT_SIZE);

            tableRowHeader.getCell(record).setColor(TABLE_HEADER_FONT_COLOR);
          } else {
            XWPFParagraph paragraph = tableRowHeader.createCell().addParagraph();
            paragraph.setAlignment(poiParameter.getAlignment());
            XWPFRun paragraphRun = paragraph.createRun();
            this.addParagraphTextBreak(paragraphRun, poiParameter.getText());
            paragraphRun.setColor(TEXT_FONT_COLOR);
            if (poiParameter.getBold() != null) {
              paragraphRun.setBold(poiParameter.getBold());
            } else {
              paragraphRun.setBold(true);
            }
            paragraphRun.setFontFamily(FONT_TYPE);
            paragraphRun.setFontSize(TABLE_TEXT_FONT_SIZE);

            tableRowHeader.getCell(record).setColor(TABLE_HEADER_FONT_COLOR);
          }
        } else {
          XWPFParagraph paragraph = tableRowHeader.getCell(record).addParagraph();
          paragraph.setAlignment(poiParameter.getAlignment());
          XWPFRun paragraphRun = paragraph.createRun();
          this.addParagraphTextBreak(paragraphRun, poiParameter.getText());
          paragraphRun.setColor(TEXT_FONT_COLOR);
          if (poiParameter.getBold() != null) {
            paragraphRun.setBold(poiParameter.getBold());
          } else {
            paragraphRun.setBold(true);
          }
          paragraphRun.setFontFamily(FONT_TYPE);
          paragraphRun.setFontSize(TABLE_TEXT_FONT_SIZE);


          tableRowHeader.getCell(record).setColor(TABLE_HEADER_FONT_COLOR);
        }
        record++;
      }
      headerIndex++;
      record = 0;
    }

    for (List<POIField> poiParameters : sData) {
      record = 0;

      // Condition for table b cell color in fields 5 and 6
      if (tableType.equals("tableBAnnualReport") && (record == 4 || record == 5)) {
        TABLE_HEADER_FONT_COLOR = "DEEAF6";
      } else {
        TABLE_HEADER_FONT_COLOR = "FFF2CC";
      }

      XWPFTableRow dataRow = table.createRow();
      for (POIField poiParameter : poiParameters) {
        count++;
        XWPFParagraph paragraph = dataRow.getCell(record).addParagraph();
        paragraph.setAlignment(poiParameter.getAlignment());
        // Hyperlink
        if (poiParameter.getUrl() != null && !poiParameter.getUrl().isEmpty()) {
          this.textHyperlink(poiParameter.getUrl(), poiParameter.getText(), paragraph);
        } else {
          XWPFRun paragraphRun = paragraph.createRun();
          this.addParagraphTextBreak(paragraphRun, poiParameter.getText());
          if (poiParameter.getFontColor() != null) {
            paragraphRun.setColor(poiParameter.getFontColor());
          } else {
            paragraphRun.setColor(TEXT_FONT_COLOR);
          }
          paragraphRun.setFontFamily(FONT_TYPE);
          paragraphRun.setFontSize(TABLE_TEXT_FONT_SIZE);

          // Condition for table b cell color in fields 5 and 6
          if (tableType.equals("tableBAnnualReport") && (record == 4 || record == 5)) {
            TABLE_HEADER_FONT_COLOR = "DEEAF6";
            dataRow.getCell(record).setColor("DEEAF6");
          } else {
            TABLE_HEADER_FONT_COLOR = "FFF2CC";
          }

          // highlight and bold first and SecondColumn for table D1
          if (tableType.equals("tableD1AnnualReport") && (record == 0 || record == 1) && count < 9) {
            dataRow.getCell(record).setColor("DEEAF6");
            paragraphRun.setBold(true);
          } else if (tableType.equals("tableD1AnnualReport") && count >= 9 && (record == 0 || record == 1)) {
            dataRow.getCell(record).setColor("E2EFD9");
            paragraphRun.setBold(true);

          } else {
            if (highlightFirstColumn && record == 0) {
              dataRow.getCell(record).setColor(TABLE_HEADER_FONT_COLOR);
              if (poiParameter.getBold() != null) {
                paragraphRun.setBold(poiParameter.getBold());
              } else {
                paragraphRun.setBold(true);
              }
            } else {
              if (poiParameter.getBold() != null) {
                paragraphRun.setBold(poiParameter.getBold());
              } else {
                paragraphRun.setBold(false);
              }
            }
          }


        }
        record++;
      }

    }

    switch (tableType) {
      case "tableA":
        this.tableAStyle(table);
        break;
      case "tableE":
        this.tableEStyle(table);
        break;
      case "tableC":
        this.tableCStyle(table);
        break;
      case "tableF":
        this.tableFStyle(table);
        break;
      case "tableG":
        this.tableGStyle(table);
        break;

      case "tableAAnnualReport":
        this.tableBAnnualReportStyle(table);
        break;
      case "tableA1AnnualReport":
        this.tableA1AnnualReportStyle(table);
        break;
      case "tableA2AnnualReport":
        this.tableA1AnnualReportStyle(table);
        break;
      case "tableBAnnualReport":
        this.tableBAnnualReportStyle(table);
        break;
      case "tableCAnnualReport":
        count = 0;
        this.tableCStyle(table);
        break;
      case "tableD1AnnualReport":
        this.tableD1AnnualReportStyle(table);
        break;
      case "tableD2AnnualReport":
        count = 0;
        this.tableAStyle(table);
        break;
      case "tableEAnnualReport":
        this.tableGStyle(table);
        break;
      case "tableFAnnualReport":
        this.tableFStyle(table);
        break;
      case "tableGAnnualReport":
        this.tableGStyle(table);
        break;
      case "tableHAnnualReport":
        this.tableGStyle(table);
        break;
      case "tableIAnnualReport":
        this.tableIAnnualReportStyle(table);
        break;
      case "tableJAnnualReport":
        this.tableJAnnualReportStyle(table);
        break;
    }
    if (tableType.contains("AnnualReport")) {
      table.getCTTbl().addNewTblPr().addNewTblW().setW(BigInteger.valueOf(13350));
    } else {
      table.getCTTbl().addNewTblPr().addNewTblW().setW(BigInteger.valueOf(12000));
    }

  }

}
