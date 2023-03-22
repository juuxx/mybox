package com.numble.mybox.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "user_file_usage")
public class UserFileUsage {

	/**
	 * id
	 */
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * user id
	 */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	/**
	 * 총 사용가능 용량
	 */
	@Column(name = "total_usage", nullable = false)
	private BigDecimal totalUsage;

	/**
	 * 사용한 용량
	 */
	@Column(name = "used_usage", nullable = false)
	private BigDecimal usedUsage;

	/**
	 * 삭제유무 0:정상, 1:삭제
	 */
	@Column(name = "remove")
	private Boolean remove;

	public void updateUsedUsage(long fileSize) {
		this.usedUsage = this.usedUsage.add(new BigDecimal(fileSize));
	}
}
