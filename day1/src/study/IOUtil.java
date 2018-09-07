package study;

import java.io.Closeable;
import java.sql.SQLException;

/**
 * User: kimkm
 * Date: 2018-08-18
 * Time: 오전 11:29
 */
public class IOUtil
{
	public static void closeAll(Object... objs)
	{
		for(Object o : objs)
		{
			if (o != null)
			{
				try
				{
					if(o instanceof Closeable )
						((Closeable)o).close();
					else if(o instanceof AutoCloseable)
						((AutoCloseable) o).close();
				}
				catch (Exception ignore)
				{

				}
			}
		}
	}
}
