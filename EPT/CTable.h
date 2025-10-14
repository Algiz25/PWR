#ifndef EPTLIST1_CTABLE_H
#define EPTLIST1_CTABLE_H
#include <iostream>
#include <string>
using namespace std;
#endif //EPTLIST1_CTABLE_H

class CTable {
public:
    string s_name;
    int i_table_len;
    int *i_table;

    CTable();

    CTable(string sName, int iTableLen);

    CTable(const CTable &pcOther);

    ~CTable();

    void vSetName(string sName);

    bool bSetNewSize(int iTableLen);

    CTable *pcClone();
};

void v_mod_tab(CTable *pcTab, int iNewSize);

void v_mod_tab(CTable cTab, int iNewSize);

void print_tab(const CTable &cTab);