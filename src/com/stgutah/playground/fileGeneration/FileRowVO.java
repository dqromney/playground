package com.stgutah.playground.fileGeneration;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents a row in the file.
 *
 * User: dqromney
 * Date: Aug 13, 2010
 * Time: 3:39:34 PM
 */
public class FileRowVO
{
    private List<FileColumnVO> list;

    /**
     * Getter method for list.
     *
     * @return the list
     */
    public List<FileColumnVO> getList()
    {
        return list;
    }

    /**
     * Setter method for list.
     *
     * @param pList
     *            the list to set
     */
    public void setList(List<FileColumnVO> pList)
    {
        this.list = pList;
    }

    /**
     * Add column data to row.
     *
     * @param pData object to be inserted
     */
    public void addData(FileColumnVO pData)
    {
        if (list == null)
        {
            list = new ArrayList<FileColumnVO>();
        }
        list.add(pData);
    }

    /**
     * Add column data to row, order the column values, by using an index.
     *
     * @param pIndex index at which the specified element is to be inserted (0...n based)
     * @param pData object to be inserted
     */
    public void addData(int pIndex, FileColumnVO pData)
    {
        if (list == null)
        {
            list = new ArrayList<FileColumnVO>(pIndex);
        }
        // Determine if index is out of range
        if(pIndex >= list.size())
        {
            // add blank data up to the point of index
            for(int index = list.size(); index <= pIndex; index ++)
            {
                list.add(index, new FileColumnVO());
            }
        }
        // Set the actual data
        list.set(pIndex, pData);
    }

    /**
     * Remove column object from row by object.
     *
     * @param pData object to be removed
     */
    public void removeData(FileColumnVO pData)
    {
        list.remove(pData);
    }

    /**
     * Remove column object from row by index.
     *
     * @param pIndex index at which the specified element is to be removed (0...n based)
     */
    public void removeData(int pIndex)
    {
        list.remove(pIndex);
    }
}

