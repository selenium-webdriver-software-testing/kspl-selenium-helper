package in.mayurshah.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * This file is used to zip your directory.
 * @author qamate
 */
public class ZipUtil {
	private Log log;

	/**
	 *
	 * @param log -Pass your log instence
     */
	public ZipUtil(Log log) {
		this.log = log;
	}

	/**
	 * This function adds all files inside the directory to your existing list of files
	 * @param dir -Enter the directory from which you want to list files
	 * @param fileList -pass your existing list where we will append new identified files
     */
	public void getAllFiles(File dir, List<File> fileList) {
		try {
			File[] files = dir.listFiles();
			for (File file : files) {
				fileList.add(file);
				if (file.isDirectory()) {
					log.write("directory:" + file.getCanonicalPath());
					getAllFiles(file, fileList);
				} else {
					log.write("     file:" + file.getCanonicalPath());
				}
			}
		} catch (IOException e) {
			log.write(e);
		}
	}

	/**
	 * This function helps you create zip file from list of files
	 * @param directoryToZip -Enter location of the directory you want to zip
	 * @param fileList -Enter list of files you want to zip from that directory
     * @return -Returns location of zip file.
     */
	public String writeZipFile(File directoryToZip, List<File> fileList) {
		String zipFileName =
				directoryToZip.getName() + "_" + UUID.randomUUID() + ".zip";
		try {
			FileOutputStream fos = new FileOutputStream(
					zipFileName);
			ZipOutputStream zos = new ZipOutputStream(fos);

			for (File file : fileList) {
				if (!file.isDirectory()) { // we only zip files, not directories
					addToZip(directoryToZip, file, zos);
				}
			}

			zos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			log.write(e);
		} catch (IOException e) {
			log.write(e);
		}
		return zipFileName;
	}

	private void addToZip(File directoryToZip, File file, ZipOutputStream zos)
			throws FileNotFoundException, IOException {

		FileInputStream fis = new FileInputStream(file);
		String zipFilePath = file.getCanonicalPath().substring(
				directoryToZip.getCanonicalPath().length() + 1,
				file.getCanonicalPath().length());
		log.write("Adding '" + zipFilePath + "' to zip file");
		ZipEntry zipEntry = new ZipEntry(zipFilePath);
		zos.putNextEntry(zipEntry);

		byte[] bytes = new byte[1024];
		int length;
		while ((length = fis.read(bytes)) >= 0) {
			zos.write(bytes, 0, length);
		}

		zos.closeEntry();
		fis.close();
	}
}
