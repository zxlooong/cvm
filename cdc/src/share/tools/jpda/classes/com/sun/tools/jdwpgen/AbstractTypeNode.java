/*
 * @(#)AbstractTypeNode.java	1.12 06/10/25
 *
 * Copyright  1990-2008 Sun Microsystems, Inc. All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License version
 * 2 only, as published by the Free Software Foundation. 
 * 
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License version 2 for more details (a copy is
 * included at /legal/license.txt). 
 * 
 * You should have received a copy of the GNU General Public License
 * version 2 along with this work; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA 
 * 
 * Please contact Sun Microsystems, Inc., 4150 Network Circle, Santa
 * Clara, CA 95054 or visit www.sun.com if you need additional
 * information or have any questions. 
 */

package com.sun.tools.jdwpgen;


import java.util.*;
import java.io.*;

abstract class AbstractTypeNode extends AbstractNamedNode 
                                implements TypeNode {

    abstract String docType();

    public abstract void genJavaWrite(PrintWriter writer, int depth, 
                                      String writeLabel);

    abstract String javaRead();

    void document(PrintWriter writer) {
        docRowStart(writer);
        writer.println("<td colspan=" + 
                       (maxStructIndent - structIndent) + ">");
        writer.println(docType() + "<td><i>" + name() + 
                       "</i><td>" + comment() + "&nbsp;");
    }

    String javaType() {
        return docType(); // default
    }

    public void genJavaRead(PrintWriter writer, int depth, 
                            String readLabel) {
        indent(writer, depth);
        writer.print(readLabel);
        writer.print(" = ");
        writer.print(javaRead());
        writer.println(";");
        genJavaDebugRead(writer, depth, readLabel, debugValue(readLabel));
    }

    public void genJavaDeclaration(PrintWriter writer, int depth) {
        writer.println();
        genJavaComment(writer, depth);
        indent(writer, depth);
        writer.print("final ");
        writer.print(javaType());
        writer.print(" " + name);
        writer.println(";");
    }

    public String javaParam() {
        return javaType() + " " + name;
    }
}
