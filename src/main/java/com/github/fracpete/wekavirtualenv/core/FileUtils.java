/*
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/*
 * FileUtils.java
 * Copyright (C) 2017 University of Waikato, Hamilton, NZ
 */

package com.github.fracpete.wekavirtualenv.core;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
 * File related utilities.
 *
 * @author FracPete (fracpete at waikato dot ac dot nz)
 */
public class FileUtils
  extends nz.ac.waikato.cms.core.FileUtils {

  /**
   * Deletes the specified file. If the file represents a directory, then this
   * will get deleted recursively.
   *
   * @param file	the file/dir to delete
   * @return		true if successfully deleted
   */
  public static boolean delete(File file) {
    boolean	result;
    File[]	files;

    result = true;

    if (file.isDirectory()) {
      files = file.listFiles();
      if (files != null) {
	for (File f : files) {
	  if (f.getName().equals(".") || f.getName().equals(".."))
	    continue;
	  result = delete(f);
	  if (!result)
	    return false;
	}
      }
    }

    result = file.delete();

    return result;
  }

  /**
   * Copies or moves files and directories (recursively).
   * If targetLocation does not exist, it will be created.
   * <br><br>
   * Original code from <a href="http://www.java-tips.org/java-se-tips/java.io/how-to-copy-a-directory-from-one-location-to-another-loc.html" target="_blank">Java-Tips.org</a>.
   *
   * @param sourceLocation	the source file/dir
   * @param targetLocation	the target file/dir
   * @param move		if true then the source files/dirs get deleted
   * 				as soon as copying finished
   * @param atomic		whether to perform an atomic move operation
   * @return			false if failed to delete when moving or failed to create target directory
   * @throws IOException	if copying/moving fails
   */
  public static boolean copyOrMove(File sourceLocation, File targetLocation, boolean move, boolean atomic) throws IOException {
    String[] 		children;
    int 		i;
    Path source;
    Path 		target;

    if (sourceLocation.isDirectory()) {
      if (!targetLocation.exists()) {
	if (!targetLocation.mkdir())
	  return false;
      }

      children = sourceLocation.list();
      for (i = 0; i < children.length; i++) {
        if (!copyOrMove(
            new File(sourceLocation.getAbsoluteFile(), children[i]),
            new File(targetLocation.getAbsoluteFile(), children[i]),
            move, atomic))
          return false;
      }

      if (move)
        return sourceLocation.delete();
      else
	return true;
    }
    else {
      source = FileSystems.getDefault().getPath(sourceLocation.getAbsolutePath());
      if (targetLocation.isDirectory())
        target = FileSystems.getDefault().getPath(targetLocation.getAbsolutePath() + File.separator + sourceLocation.getName());
      else
        target = FileSystems.getDefault().getPath(targetLocation.getAbsolutePath());
      if (move) {
	if (atomic)
	  Files.move(source, target, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
	else
	  Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);
      }
      else {
	Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
      }
      return true;
    }
  }

  /**
   * Writes the given object to the specified file. The message is either
   * appended or replaces the current content of the file.
   *
   * @param filename	the file to write to
   * @param obj		the object to write
   * @param append	whether to append the message or not
   * @param encoding	the encoding to use, null for default
   * @return		true if writing was successful
   */
  public static String writeToFileMsg(String filename, Object obj, boolean append, String encoding) {
    String			result;
    List<String> lines;
    StandardOpenOption[]	options;

    result = null;
    lines = new ArrayList<>();
    lines.add("" + obj);
    if (append)
      options = new StandardOpenOption[]{StandardOpenOption.CREATE, StandardOpenOption.APPEND, StandardOpenOption.WRITE};
    else
      options = new StandardOpenOption[]{StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE};
    try {
      if (encoding == null)
	Files.write(new File(filename).toPath(), lines, options);
      else
	Files.write(new File(filename).toPath(), lines, Charset.forName(encoding), options);
    }
    catch (Exception e) {
      result = "Failed to write to '" + filename + "'\n" + e;
    }

    return result;
  }
}
