package org.jicker.util.filebrowser.walker;
import java.io.*; 
import java.util.*; 
import java.util.regex.Pattern; 
 
public class FileFinder 
{ 
  public static void main( String[] args ) 
  { 
    String path = new File( "e:/bilder/s45-bilder" ).getPath(); 
 
    System.out.println( "Looking in path: " + path ); 
 
    FileFinder ff = new FileFinder(); 
    List<File> files = ff.find( path, "(.*\\.gif$)|(.*\\.jpg$)" ); 
 
    System.out.printf( "Found %d file%s.%n", 
                       files.size(), files.size() == 1 ? "" : "s" ); 
    for ( File f : files ) 
      System.out.println( f.getAbsolutePath() ); 
    System.out.println(files.size());
  } 
 
  public List<File> find( String start, String extensionPattern ) 
  { 
    final List<File> files = new ArrayList<File>( 1024 ); 
    final Stack<File> dirs = new Stack<File>(); 
    final File startdir = new File( start ); 
    final Pattern p = Pattern.compile( extensionPattern, Pattern.CASE_INSENSITIVE ); 
 
    if ( startdir.isDirectory() ) 
      dirs.push( startdir ); 
 
    while ( dirs.size() > 0 ) 
    { 
      for ( File file : dirs.pop().listFiles() ) 
      { 
        if ( file.isDirectory() ) 
          dirs.push( file ); 
        else 
          if ( p.matcher(file.getName()).matches() ) 
            files.add( file ); 
      } 
    } 
 
    return files; 
  } 
}