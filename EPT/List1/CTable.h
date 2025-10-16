#ifndef EPTLIST1_CTABLE_H
#define EPTLIST1_CTABLE_H
#include <iostream>
#include <string>
using namespace std;
#endif //EPTLIST1_CTABLE_H

class CTable {
public:
    CTable();

    CTable(string sName, int iTableLen);

    CTable(const CTable &pcOther);

    ~CTable();

    void vSetName(string sName);

    bool bSetNewSize(int iTableLen);

    CTable *pcClone();

private:
    string s_name;
    int i_table_len;
    int *i_table;
};

void v_mod_tab(CTable *pcTab, int iNewSize);

void v_mod_tab(CTable cTab, int iNewSize);
