def str = 'abstract\targuments\tboolean\tbreak\tbyte\tcase\tcatch\tchar\tclass*\tconst\tcontinue\tdebugger\tdefault\tdelete\tdo\tdouble\telse\tenum*\teval\texport*\textends*\tfalse\tfinal\tfinally\tfloat\tfor\tfunction\tgoto\tif\timplements\timport*\tin\tinstanceof\tint\tinterface\tlet\tlong\tnative\tnew\tnull\tpackage\tprivate\tprotected\tpublic\treturn\tshort\tstatic\tsuper*\tswitch\tsynchronized\tthis\tthrow\tthrows\ttransient\ttrue\ttry\ttypeof\tvar\tvoid\tvolatile\twhile\twith\tyield'

str.split("\t").each {
    if (it.endsWith("*"))
        it = it.substring(0, it.length() - 1)
    print "\"${it}\","
}

