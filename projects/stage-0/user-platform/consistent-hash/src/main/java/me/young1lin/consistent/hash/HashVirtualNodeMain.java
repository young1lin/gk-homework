package me.young1lin.consistent.hash;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 原文
 * <a>https://segmentfault.com/a/1190000022792290</a>
 *
 * @author <a href="mailto:young1lin0108@gmail.com">young1lin</a>
 * @since 2021/5/19 下午6:12
 * @version 1.0
 */
public class HashVirtualNodeMain {

	/**
	 * 实际的服务器
	 */
	private static final String[] SERVERS = {"192.168.0.1:8999", "192.168.0.2:8999", "192.168.0.0.3:8999"};

	/**
	 *每个服务节点对应的虚拟服务节点数目
	 */
	private final static int VIRTUAL_NODE = 5;

	/**
	 * 哈希环上的虚拟服务节点
	 * key 虚拟服务节点的hash值
	 * value 虚拟服务节点名称
	 */
	private static final SortedMap<Integer, String> VIRTUAL_NODE_MAP = new TreeMap<>();

	static {
		// 将虚拟节点放到virtualNodeMap中
		// 虚拟节点与实际的服务器的对应关系
		// 服务器名称：server
		// 虚拟节点名：server&&Node0 server&&Node1 server&&Node2 ...
		for (String server : SERVERS) {
			for (int j = 0; j < VIRTUAL_NODE; j++) {
				String virtualNodeName = server + "&&" + "Node" + j;
				Integer hash = fnv132Hash(virtualNodeName);
				VIRTUAL_NODE_MAP.put(hash, virtualNodeName);
				System.out.println("hash=" + hash + " virtualNode=" + virtualNodeName);
			}
		}
	}

	/**
	 * 使用FNV1_32_HASH算法计算hash值
	 *
	 * @param str 虚拟节点名字
	 * @return hash 值
	 */
	public static Integer fnv132Hash(String str) {
		final int p = 1677769;

		int hash = 0;
		int len = str.length();
		for (int i = 0; i < len; i++) {
			hash = (hash ^ str.charAt(i)) * p;
		}
		hash += hash << 13;
		hash ^= hash >> 7;
		hash += hash << 3;
		hash ^= hash >> 17;
		hash += hash << 5;

		if (hash < 0) {
			hash = Math.abs(hash);
		}
		return hash;
	}

	/**
	 * 获取一个节点对应的分配到的服务节点
	 *
	 * @param node 待查询的节点
	 * @return 实际的服务节点名称
	 */
	public static String getServer(String node) {
		Integer hash = fnv132Hash(node);

		//获取大于当前节点的虚拟节点服务器
		SortedMap<Integer, String> tailMap = VIRTUAL_NODE_MAP.tailMap(hash);

		Integer key;
		if (VIRTUAL_NODE_MAP.isEmpty()) {
			//没有大于当前节点的虚拟服务节点时，取虚拟服务节点的第一个
			key = VIRTUAL_NODE_MAP.firstKey();
		}
		else {
			//取出大于当前节点的第一个虚拟服务节点
			key = tailMap.firstKey();
		}
		//取出对应的虚拟节点
		String virtualNode = VIRTUAL_NODE_MAP.get(key);

		//获取实际的服务名称
		return virtualNode.split("&&")[0];
	}

	public static void main(String[] args) {
		String[] index = {"582639584", "582639585", "582639586", "582639587", "582639588"};

		System.out.println("------------------");
		for (String s : index) {
			String server = getServer(s);
			System.out.println("index=" + s + " hash=" + fnv132Hash(s) + " server=" + server);
		}
	}

}
